package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.base.BaseQuery;
import com.softeem.crm.mapper.CustomerOrderMapper;
import com.softeem.crm.pojo.Customer;
import com.softeem.crm.pojo.CustomerLoss;
import com.softeem.crm.pojo.CustomerOrder;
import com.softeem.crm.service.CustomerLossService;
import com.softeem.crm.service.CustomerService;
import com.softeem.crm.mapper.CustomerMapper;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.utils.PhoneUtil;
import com.softeem.crm.vo.CustomerQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangw
 * @description 针对表【t_customer】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:59
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
        implements CustomerService {
    @Resource
    private CustomerOrderMapper customerOrderMapper;
    @Resource
    private CustomerLossService customerLossService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomerState() {
        List<Customer> lossCutomers = this.baseMapper.queryLossCustomers();
        if (null != lossCutomers && lossCutomers.size() > 0) {
            List<CustomerLoss> customerLosses = new ArrayList<CustomerLoss>();
            List<Integer> lossCusIds = new ArrayList<Integer>();
            lossCutomers.forEach(customer -> {
                CustomerLoss customerLoss = new CustomerLoss();
                LambdaQueryWrapper<CustomerOrder> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(CustomerOrder::getCusId, customer.getId())
                        .orderByDesc(CustomerOrder::getOrderDate)
                        .last("limit 0,1");
                // 设置最后下单时间
                //CustomerOrder lastCustomerOrder = customerOrderMapper.queryLastCustomerOrderByCusId(customer.getId());
                CustomerOrder lastCustomerOrder = customerOrderMapper.selectOne(queryWrapper);
                if (null != lastCustomerOrder) {
                    customerLoss.setLastOrderTime(lastCustomerOrder.getOrderDate());
                }
                //customerLoss.setCreateDate(new Date());
                customerLoss.setCusManager(customer.getCusManager());
                customerLoss.setCusName(customer.getName());
                customerLoss.setCusNo(customer.getKhno());
                customerLoss.setIsValid(0);
                //  设置客户流失状态为暂缓流失状态
                customerLoss.setState(0);
                //customerLoss.setUpdateDate(new Date());
                customerLosses.add(customerLoss);
                lossCusIds.add(customer.getId());
            });
            AssertUtil.isTrue(!customerLossService.saveBatch(customerLosses), "客户流失数据流转失败!");
            LambdaUpdateWrapper<Customer> updateWrapper = Wrappers.<Customer>lambdaUpdate().set(Customer::getState, 1)
                    .in(Customer::getId, lossCusIds);
            AssertUtil.isTrue(this.baseMapper.update(null, updateWrapper) < lossCusIds.size(), "客户流失数据流转失败!");
        }
    }

    public Map<String, Object> queryByParamsForTable(CustomerQuery customerQuery) {
        Map<String, Object> result = new HashMap<String, Object>();
        Page<Customer> page = new Page<>(customerQuery.getPage(), customerQuery.getLimit());
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(customerQuery.getCusName()), Customer::getName, customerQuery.getCusName())
                .eq(StringUtils.isNotBlank(customerQuery.getCusNo()), Customer::getKhno, customerQuery.getCusNo())
                .eq(StringUtils.isNotBlank(customerQuery.getLevel()), Customer::getLevel, customerQuery.getLevel())
                .orderByDesc(Customer::getId);
        Page<Customer> customerPage = this.baseMapper.selectPage(page, queryWrapper);
        result.put("count", customerPage.getTotal());
        result.put("data", customerPage.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCustomer(Customer customer) {
        checkParams(customer.getName(), customer.getPhone(), customer.getFr());
        AssertUtil.isTrue(null != this.baseMapper.selectOne(Wrappers.<Customer>lambdaQuery()
                .eq(Customer::getName, customer.getName())), "该客户已存在!");
        customer.setIsValid(0);
        customer.setState(0);
        /*customer.setCreateDate(new Date());
        customer.setUpdateDate(new Date());*/
        String khno = "KH_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        customer.setKhno(khno);
        AssertUtil.isTrue(baseMapper.insert(customer) < 1, "客户添加失败!");
    }

    private void checkParams(String name, String phone, String fr) {
        AssertUtil.isTrue(StringUtils.isBlank(name), "请指定客户名称!");
        AssertUtil.isTrue(!(PhoneUtil.isMobile(phone)), "手机号格式非法！");
        AssertUtil.isTrue(StringUtils.isBlank(fr), "请指定公司法人!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateCustomer(Customer customer) {
        AssertUtil.isTrue(null == customer.getId() || null == getById(customer.getId()), "待更新记录不存在!");
        checkParams(customer.getName(), customer.getPhone(), customer.getFr());
        Customer temp = baseMapper.selectOne(Wrappers.<Customer>lambdaQuery().eq(Customer::getName, customer.getName()));
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(customer.getId())), "该客户已存在!");
        customer.setUpdateDate(new Date());
        AssertUtil.isTrue(baseMapper.updateById(customer) < 1, "客户更新失败!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteCustomer(Integer cid) {
        Customer customer = getById(cid);
        AssertUtil.isTrue(null == cid || null == cid, "待删除记录不存在!");
        /**
         * 如果客户被删除
         *     级联 客户联系人 客户交往记录 客户订单  被删除
         *
         * 如果客户被删除
         *     如果子表存在记录  不支持删除
         */
        //customer.setIsValid(0);
        AssertUtil.isTrue(!removeById(customer), "客户删除失败!");
    }

    public Map<String, Object> queryCustomerContributionByParams(CustomerQuery customerQuery) {
        Map<String, Object> result = new HashMap<String, Object>();
        Page<Customer> page = new Page<>(customerQuery.getPage(), customerQuery.getLimit());
        IPage<Map<String, Object>> mapIPage = baseMapper.queryCustomerContributionByParams(page, customerQuery);
        result.put("count", mapIPage.getTotal());
        result.put("data", mapIPage.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    // 折线图数据处理
    public Map<String, Object> countCustomerMake() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = baseMapper.countCustomerMake();
        List<String> data1List = new ArrayList<String>();
        List<Integer> data2List = new ArrayList<Integer>();
        list.forEach(m -> {
            data1List.add(m.get("level").toString());
            data2List.add(Integer.parseInt(m.get("total") + ""));
        });
        result.put("data1", data1List);
        result.put("data2", data2List);
        return result;
    }


    // 饼状图数据处理
    public Map<String, Object> countCustomerMake02() {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = baseMapper.countCustomerMake();
        List<String> data1List = new ArrayList<String>();
        List<Map<String, Object>> data2List = new ArrayList<Map<String, Object>>();
        list.forEach(m -> {
            data1List.add(m.get("level").toString());
            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("name", m.get("level"));
            temp.put("value", m.get("total"));
            data2List.add(temp);
        });
        result.put("data1", data1List);
        result.put("data2", data2List);
        return result;
    }
}




