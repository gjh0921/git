package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.pojo.OrderDetails;
import com.softeem.crm.service.OrderDetailsService;
import com.softeem.crm.mapper.OrderDetailsMapper;
import com.softeem.crm.vo.OrderDetailsQuery;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_order_details】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:59
 */
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails>
        implements OrderDetailsService {
    @Override
    public Map<String, Object> queryByParamsForTable(OrderDetailsQuery orderDetailsQuery) {
        Page<OrderDetails> param = new Page<>(orderDetailsQuery.getPage(), orderDetailsQuery.getLimit());

        Page<OrderDetails> page = baseMapper.selectPage(param, Wrappers.<OrderDetails>lambdaQuery().eq(OrderDetails::getOrderId, orderDetailsQuery.getOrderId())
                .orderByDesc(OrderDetails::getId));

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("count", page.getTotal());
        result.put("data", page.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }
}




