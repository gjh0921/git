package com.softeem.crm.service;

import com.softeem.crm.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.vo.CustomerQuery;

import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_customer】的数据库操作Service
 * @createDate 2022-12-27 14:22:59
 */
public interface CustomerService extends IService<Customer> {
    public void updateCustomerState();

    Map<String, Object> queryByParamsForTable(CustomerQuery customerQuery);

    public void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Integer id);

    public Map<String, Object> queryCustomerContributionByParams(CustomerQuery customerQuery);

    public Map<String, Object> countCustomerMake();

    public Map<String, Object> countCustomerMake02();
}
