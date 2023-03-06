package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.Customer;
import com.softeem.crm.service.CustomerOrderService;
import com.softeem.crm.service.CustomerService;
import com.softeem.crm.vo.CustomerQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerOrderService orderService;


    @RequestMapping("/index")
    public String index() {
        return "/customer/customer";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryCustomersByParams(CustomerQuery customerQuery) {
        return customerService.queryByParamsForTable(customerQuery);
    }

    @RequestMapping("/addOrUpdateSaleChancePage")
    public String addOrUpdateSaleChancePage(Integer id, Model model) {
        model.addAttribute("customer", customerService.getById(id));
        return "/customer/add_update";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultInfo saveCustomer(Customer customer) {
        customerService.saveCustomer(customer);
        return success("客户添加成功");
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultInfo updateCustomer(Customer customer) {
        customerService.updateCustomer(customer);
        return success("客户更新成功");
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo deleteCustomer(Integer id) {
        customerService.deleteCustomer(id);
        return success("客户删除成功");
    }

    @RequestMapping("/orderInfoPage")
    public String showOrderInfo(Integer cid, Model model) {
        model.addAttribute("customer", customerService.getById(cid));
        return "/customer/customer_order";
    }

    @RequestMapping("/orderDetailPage")
    public String orderDetailPage(Integer orderId, Model model) {
        model.addAttribute("order", orderService.queryOrderDetailByOrderId(orderId));
        return "/customer/customer_order_detail";
    }

    @RequestMapping("/linkmanPage")
    public String linkmanPage(Integer cid, Model model) {
        model.addAttribute("customer", customerService.getById(cid));
        return "/customerLinkman/customer_linkman";
    }

    @RequestMapping("/cusContcatPage")
    public String cusContcatPage(Integer cid, Model model) {
        model.addAttribute("customer", customerService.getById(cid));
        return "/customerContcat/customer_contcat";
    }

    /**
     * 客户贡献分析
     */
    @RequestMapping("queryCustomerContributionByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerContributionByParams(CustomerQuery customerQuery) {
        return customerService.queryCustomerContributionByParams(customerQuery);
    }

    // 折线图接口地址
    @RequestMapping("countCustomerMake")
    @ResponseBody
    public Map<String, Object> countCustomerMake() {
        return customerService.countCustomerMake();
    }

    // 饼状图接口地址
    @RequestMapping("countCustomerMake02")
    @ResponseBody
    public Map<String, Object> countCustomerMake02() {
        return customerService.countCustomerMake02();
    }
}