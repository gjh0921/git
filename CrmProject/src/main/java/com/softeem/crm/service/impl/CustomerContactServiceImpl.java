package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.pojo.CustomerContact;
import com.softeem.crm.service.CustomerContactService;
import com.softeem.crm.mapper.CustomerContactMapper;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.CustomerContactQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_customer_contact】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:59
 */
@Service
public class CustomerContactServiceImpl extends ServiceImpl<CustomerContactMapper, CustomerContact>
        implements CustomerContactService {
    @Override
    public Map<String, Object> queryCustomerContcatByCusId(CustomerContactQuery customerContactQuery) {
        Page<CustomerContact> param = new Page<>(customerContactQuery.getPage(), customerContactQuery.getLimit());
        Page<CustomerContact> page = baseMapper.selectPage(param, Wrappers.<CustomerContact>lambdaQuery().eq(CustomerContact::getCusId, customerContactQuery.getCusId()));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("count", page.getTotal());
        result.put("data", page.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Override
    public void saveOrUpdateCusLinkman(CustomerContact customerContact) {
        checkCusLinkmanInfo(customerContact);
        AssertUtil.isTrue(!saveOrUpdate(customerContact), "操作联系人信息失败!");
    }

    private void checkCusLinkmanInfo(CustomerContact customerContact) {
        AssertUtil.isTrue(StringUtils.isBlank(customerContact.getAddress()), "请输入交往地址");
        AssertUtil.isTrue(StringUtils.isBlank(customerContact.getOverview()), "请输入交往事件");
        AssertUtil.isTrue(customerContact.getContactTime() == null, "请输入交往时间");
        AssertUtil.isTrue(customerContact.getCusId() == null, "请指定客户id!");
    }
}




