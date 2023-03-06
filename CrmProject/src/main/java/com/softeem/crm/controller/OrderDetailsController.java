package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.service.OrderDetailsService;
import com.softeem.crm.vo.OrderDetailsQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/order_details")
public class OrderDetailsController extends BaseController {

    @Resource
    private OrderDetailsService orderDetailsService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryOrderDetailsByParams(OrderDetailsQuery orderDetailsQuery) {
        return orderDetailsService.queryByParamsForTable(orderDetailsQuery);
    }
}