package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.pojo.CustomerOrder;
import com.softeem.crm.service.CustomerOrderService;
import com.softeem.crm.mapper.CustomerOrderMapper;
import com.softeem.crm.vo.CustomerOrderQuery;
import com.softeem.crm.vo.CustomerOrderVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_customer_order】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:59
 */
@Service
public class CustomerOrderServiceImpl extends ServiceImpl<CustomerOrderMapper, CustomerOrder>
        implements CustomerOrderService {
    @Override
    public Map<String, Object> queryByParamsForTable(CustomerOrderQuery customerOrderQuery) {
        Page<CustomerOrder> param = new Page<>(customerOrderQuery.getPage(), customerOrderQuery.getLimit());
        Page<CustomerOrder> page = baseMapper.selectPage(param, Wrappers.<CustomerOrder>lambdaQuery().eq(CustomerOrder::getCusId, customerOrderQuery.getCid()).orderByDesc(CustomerOrder::getId));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("count", page.getTotal());
        result.put("data", page.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Override
    public CustomerOrderVo queryOrderDetailByOrderId(Integer orderId) {
        return baseMapper.queryOrderDetailByOrderId(orderId);
    }
}




