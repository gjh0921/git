package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.pojo.CustomerLinkman;
import com.softeem.crm.service.CustomerLinkmanService;
import com.softeem.crm.mapper.CustomerLinkmanMapper;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.CustomerLinkmanQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_customer_linkman】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:59
 */
@Service
public class CustomerLinkmanServiceImpl extends ServiceImpl<CustomerLinkmanMapper, CustomerLinkman>
        implements CustomerLinkmanService {

    @Override
    public Map<String, Object> queryCustomerLinkmanByCusId(CustomerLinkmanQuery customerLinkmanQuery) {
        Page<CustomerLinkman> param = new Page<>(customerLinkmanQuery.getPage(), customerLinkmanQuery.getLimit());
        Page<CustomerLinkman> page = baseMapper.selectPage(param, Wrappers.<CustomerLinkman>lambdaQuery()
                .eq(CustomerLinkman::getCusId, customerLinkmanQuery.getCusId()));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("count", page.getTotal());
        result.put("data", page.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Override
    public void saveOrUpdateCusLinkman(CustomerLinkman customerLinkman) {
        checkCusLinkmanInfo(customerLinkman);
        AssertUtil.isTrue(!saveOrUpdate(customerLinkman), "操作联系人信息失败!");
    }

    private void checkCusLinkmanInfo(CustomerLinkman customerLinkman) {
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkman.getLinkName()), "请输入联系人姓名");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkman.getPhone()), "请输入联系人联系电话");
        AssertUtil.isTrue(StringUtils.isBlank(customerLinkman.getSex()), "请输入联系人性别");
        AssertUtil.isTrue(customerLinkman.getCusId() == null, "请指定客户id!");
    }
}




