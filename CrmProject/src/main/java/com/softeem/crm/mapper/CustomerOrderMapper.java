package com.softeem.crm.mapper;

import com.softeem.crm.pojo.CustomerOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softeem.crm.vo.CustomerOrderVo;

/**
* @author wangw
* @description 针对表【t_customer_order】的数据库操作Mapper
* @createDate 2022-12-27 14:22:59
* @Entity com.softeem.crm.pojo.CustomerOrder
*/
public interface CustomerOrderMapper extends BaseMapper<CustomerOrder> {

    CustomerOrderVo queryOrderDetailByOrderId(Integer orderId);
}




