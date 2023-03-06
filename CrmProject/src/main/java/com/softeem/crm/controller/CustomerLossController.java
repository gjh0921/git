package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.CustomerLoss;
import com.softeem.crm.service.CustomerLossService;
import com.softeem.crm.vo.CustomerLossQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/customer_loss")
public class CustomerLossController extends BaseController {
    @Resource
    private CustomerLossService customerLossService;

    @RequestMapping("/index")
    private String index() {
        return "/customerLoss/customer_loss";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(CustomerLossQuery customerLossQuery) {
        Map<String, Object> map = customerLossService.queryByParamsForTable(customerLossQuery);
        return customerLossService.queryByParamsForTable(customerLossQuery);
    }

    @RequestMapping("/customerLossesInfo")
    public String customerLossesInfo(Integer id, Model model) {
        model.addAttribute("customerLoss", customerLossService.getById(id));
        return "/customerLoss/customer_loss_info";
    }

    @RequestMapping("/updateCustomerLossStateById")
    @ResponseBody
    public ResultInfo updateCustomerLossStateById(CustomerLoss customerLoss) {
        customerLossService.updateState(customerLoss);
        return success();
    }
}