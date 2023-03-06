package com.softeem.crm.service;

import com.softeem.crm.pojo.CustomerLinkman;
import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.vo.CustomerLinkmanQuery;

import java.util.Map;

/**
* @author wangw
* @description 针对表【t_customer_linkman】的数据库操作Service
* @createDate 2022-12-27 14:22:59
*/
public interface CustomerLinkmanService extends IService<CustomerLinkman> {

    Map<String, Object> queryCustomerLinkmanByCusId(CustomerLinkmanQuery customerLinkmanQuery);

    void saveOrUpdateCusLinkman(CustomerLinkman customerLinkman);
}
