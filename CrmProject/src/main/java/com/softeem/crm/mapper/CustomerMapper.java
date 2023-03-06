package com.softeem.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.softeem.crm.pojo.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.crm.vo.CustomerQuery;
import com.softeem.crm.vo.CustomerServerQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_customer】的数据库操作Mapper
 * @createDate 2022-12-27 14:22:59
 * @Entity com.softeem.crm.pojo.Customer
 */
public interface CustomerMapper extends BaseMapper<Customer> {
    /**
     * 查询流失客户信息
     *
     * @return
     */
    List<Customer> queryLossCustomers();

    IPage<Map<String, Object>> queryCustomerContributionByParams(IPage page, @Param("csq") CustomerQuery customerQuery);

    List<Map<String, Object>> countCustomerMake();
}




