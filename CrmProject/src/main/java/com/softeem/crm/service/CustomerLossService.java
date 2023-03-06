package com.softeem.crm.service;

import com.softeem.crm.pojo.CustomerLoss;
import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.vo.CustomerLossQuery;

import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_customer_loss】的数据库操作Service
 * @createDate 2022-12-27 14:22:59
 */
public interface CustomerLossService extends IService<CustomerLoss> {
    Map<String, Object> queryByParamsForTable(CustomerLossQuery customerLossQuery);

    void updateState(CustomerLoss customerLoss);
}
