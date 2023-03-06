package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.enums.CustomerServeStatus;
import com.softeem.crm.mapper.CustomerMapper;
import com.softeem.crm.mapper.UserMapper;
import com.softeem.crm.pojo.Customer;
import com.softeem.crm.pojo.CustomerServer;
import com.softeem.crm.pojo.User;
import com.softeem.crm.service.CustomerServerService;
import com.softeem.crm.mapper.CustomerServerMapper;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.CustomerServerQuery;
import com.softeem.crm.vo.CustomerServerVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_customer_server】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:59
 */
@Service
public class CustomerServerServiceImpl extends ServiceImpl<CustomerServerMapper, CustomerServer>
        implements CustomerServerService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Map<String, Object> queryByParamsForTable(CustomerServerQuery customerServerQuery) {
        Page page = new Page<>(customerServerQuery.getPage(), customerServerQuery.getLimit());
        IPage<CustomerServerVo> customerServerIPage = this.baseMapper.selectByParams(page, customerServerQuery);
        /**
         * 此役操作是为了运用服务分配人的id查询到其姓名,将其赋值给页面上显示的分配人的姓名下
         */
        List<CustomerServerVo> serverVoList = customerServerIPage.getRecords();
        for (CustomerServerVo customerServerVo : serverVoList) {
            if (customerServerVo.getAssigner() != null && !customerServerVo.getAssigner().equals("")) {
                int userId = Integer.parseInt(customerServerVo.getAssigner());
                User user = userMapper.selectById(userId);
                if (user != null) {
                    customerServerVo.setAssigner(user.getUserName());
                }
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("count", customerServerIPage.getTotal());
        result.put("data", customerServerIPage.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdateCustomerServer(CustomerServer customerServer) {
        if (null == customerServer.getId()) {
            /**  服务添加操作
             * 1.参数校验
             *     客户名  非空
             *     客户类型  非空
             * 2.添加默认值
             *    state  设置状态值
             *    isValid  createDate updateDate
             *  3.执行添加 判断结果
             */
            AssertUtil.isTrue(com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(customerServer.getCustomer()), "请指定客户!");
            AssertUtil.isTrue(null == customerMapper
                    .selectOne(Wrappers.<Customer>lambdaQuery().eq(Customer::getName, customerServer.getCustomer())), "当前客户暂不存在!");
            AssertUtil.isTrue(com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(customerServer.getServeType()), "请指定服务类型!");
            customerServer.setIsValid(0);
            customerServer.setState(CustomerServeStatus.CREATED.getState());
            AssertUtil.isTrue(this.baseMapper.insert(customerServer) < 1, "服务记录添加失败!");
        } else {
            /**
             * 分配  处理  反馈
             */
            CustomerServer temp = getById(customerServer.getId());
            AssertUtil.isTrue(null == temp, "待处理的服务记录不存在!");
            if (customerServer.getState().equals(CustomerServeStatus.ASSIGNED.getState())) {
                // 服务分配
                AssertUtil.isTrue(com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(customerServer.getAssigner()) ||
                        (null == userMapper.selectById(Integer.parseInt(customerServer.getAssigner()))), "待分配用户不存在");
                customerServer.setAssignTime(new Date());
                AssertUtil.isTrue(this.baseMapper.updateById(customerServer) < 1, "服务分配失败!");
            }
            if (customerServer.getState().equals(CustomerServeStatus.PROCED.getState())) {
                // 服务处理
                AssertUtil.isTrue(com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(customerServer.getServiceProce()), "请指定处理内容!");
                customerServer.setServiceProceTime(new Date());
                AssertUtil.isTrue(this.baseMapper.updateById(customerServer) < 1, "服务处理失败!");
            }
            if (customerServer.getState().equals(CustomerServeStatus.FEED_BACK.getState())) {
                // 服务处理
                AssertUtil.isTrue(com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(customerServer.getServiceProceResult()), "请指定反馈内容!");
                AssertUtil.isTrue(StringUtils.isBlank(customerServer.getMyd()), "请指定反馈满意度!");
                customerServer.setState(CustomerServeStatus.ARCHIVED.getState());
                AssertUtil.isTrue(this.baseMapper.updateById(customerServer) < 1, "服务反馈失败!");
            }
        }

    }
}




