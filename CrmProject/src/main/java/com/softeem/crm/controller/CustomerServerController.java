package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.CustomerServer;
import com.softeem.crm.service.CustomerServerService;
import com.softeem.crm.utils.LoginUserUtil;
import com.softeem.crm.vo.CustomerServerQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/customer_server")
public class CustomerServerController extends BaseController {

    @Resource
    private CustomerServerService customerServerService;

    // 服务管理页面转发方法
    @RequestMapping("/index/{type}")
    public String index(@PathVariable Integer type) {
        if (type == 1) {
            return "/customerServer/customer_server";
        } else if (type == 2) {
            return "/customerServer/customer_server_assign";
        } else if (type == 3) {
            return "/customerServer/customer_server_proce";
        } else if (type == 4) {
            return "/customerServer/customer_server_feed_back";
        } else if (type == 5) {
            return "/customerServer/customer_server_archive";
        } else {
            return "";
        }
    }


    // 服务信息列表展示
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryCustomerServerByParams(Integer flag, HttpServletRequest request, CustomerServerQuery customerServerQuery) {
        if (null != flag && flag == 1) {
            customerServerQuery.setAssigner(LoginUserUtil.releaseUserIdFromCookie(request));
        }
        return customerServerService.queryByParamsForTable(customerServerQuery);
    }

    // 服务添加页面转发
    @RequestMapping("/addCustomerServerPage")
    public String addCustomerServerPage() {
        return "/customerServer/customer_server_add";
    }

    //服务分配页面转发
    @RequestMapping("/addCustomerServerAssignPage")
    public String addCustomerServerAssignPage(Integer id, Model model) {
        model.addAttribute("customerServer", customerServerService.getById(id));
        return "/customerServer/customer_server_assign_add";
    }

    // 服务反馈页面转发
    @RequestMapping("/addCustomerServerBackPage")
    public String addCustomerServerBackPage(Integer id, Model model) {
        model.addAttribute("customerServer", customerServerService.getById(id));
        return "/customerServer/customer_server_feed_back_add";
    }

    // 服务处理页面转发
    @RequestMapping("/addCustomerServerProcePage")
    public String addCustomerServerProcePage(Integer id, Model model) {
        model.addAttribute("customerServer", customerServerService.getById(id));
        return "/customerServer/customer_server_proce_add";
    }


    //服务添加 分配  处理 归档处理
    @RequestMapping("/saveOrUpdateCustomerServer")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomerServer(CustomerServer customerServer) {
        customerServerService.saveOrUpdateCustomerServer(customerServer);
        return success("操作成功");
    }
}