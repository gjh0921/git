package com.softeem.crm.controller;

import com.softeem.crm.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {
    @RequestMapping("/report/{type}")
    public String index(@PathVariable Integer type) {
        if (type == 0) {
            return "/report/customer_contri";
        } else if (type == 1) {
            return "/report/customer_make";
        } else if (type == 3) {
            return "/report/customer_loss";
        } else {
            return "";
        }
    }

}
