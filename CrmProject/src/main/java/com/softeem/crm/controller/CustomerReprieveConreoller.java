package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.CustomerReprieve;
import com.softeem.crm.service.CustomerLossService;
import com.softeem.crm.service.CustomerReprieveService;
import com.softeem.crm.vo.CustomerLossQuery;
import com.softeem.crm.vo.CustomerReprieveQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/cus_reprieve")
public class CustomerReprieveConreoller extends BaseController {
    @Resource
    private CustomerReprieveService customerReprieveService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(CustomerReprieveQuery customerReprieveQuery) {
        return customerReprieveService.queryByParamsForTable(customerReprieveQuery);
    }

    @RequestMapping("/addOrUpdateCusRep")
    public String addOrUpdateCusRep(Model model, Integer lossId, Integer id) {
        model.addAttribute("customerRep", customerReprieveService.getById(id));
        model.addAttribute("lossId", lossId);
        return "customerLoss/customer_rep_add_update";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdate(CustomerReprieve customerReprieve) {
        customerReprieve.setIsValid(0);
        customerReprieveService.saveOrUpdate(customerReprieve);
        return success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo delete(Integer id) {
        customerReprieveService.removeById(id);
        return success();
    }
}
