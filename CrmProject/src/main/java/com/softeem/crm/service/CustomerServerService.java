package com.softeem.crm.service;

import com.softeem.crm.pojo.CustomerServer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.vo.CustomerServerQuery;

import java.util.Map;

/**
* @author wangw
* @description 针对表【t_customer_server】的数据库操作Service
* @createDate 2022-12-27 14:22:59
*/
public interface CustomerServerService extends IService<CustomerServer> {

    Map<String, Object> queryByParamsForTable(CustomerServerQuery customerServerQuery);

    void saveOrUpdateCustomerServer(CustomerServer customerServer);
}
