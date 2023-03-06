package com.softeem.crm.service;

import com.softeem.crm.pojo.CustomerContact;
import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.vo.CustomerContactQuery;

import java.util.Map;

/**
* @author wangw
* @description 针对表【t_customer_contact】的数据库操作Service
* @createDate 2022-12-27 14:22:59
*/
public interface CustomerContactService extends IService<CustomerContact> {

    Map<String, Object> queryCustomerContcatByCusId(CustomerContactQuery customerContactQuery);

    void saveOrUpdateCusLinkman(CustomerContact customerContact);
}
