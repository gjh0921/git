package com.softeem.crm.controller;


import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.CustomerContact;
import com.softeem.crm.service.CustomerContactService;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.CustomerContactQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/customerContcat")
public class CustomerContcatController extends BaseController {

    @Autowired
    private CustomerContactService customerContactService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(CustomerContactQuery customerContactQuery){
        return customerContactService.queryCustomerContcatByCusId(customerContactQuery);
    }

    @RequestMapping("/addOrUpdateCusContcatPage")
    public String addOrUpdateCusLinkmanPage(Integer cid, Integer id, Model model){
        model.addAttribute("customerContcat",customerContactService.getById(id));
        model.addAttribute("cid", cid);
        return "/customerContcat/add_update";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdate(CustomerContact customerContact){
        customerContact.setIsValid(0);
        customerContactService.saveOrUpdateCusLinkman(customerContact);
        return success();
    }
    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[] ids){
        AssertUtil.isTrue(null == ids || ids.length==0,"请选择待删除的联系人记录!");
        customerContactService.removeBatchByIds(Arrays.asList(ids));
        return success("联系人删除成功");
    }

}
