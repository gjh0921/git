package com.softeem.crm.controller;


import com.softeem.crm.base.BaseController;
import com.softeem.crm.base.ResultInfo;
import com.softeem.crm.pojo.CustomerLinkman;
import com.softeem.crm.service.CustomerLinkmanService;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.CustomerLinkmanQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/customerLinkman")
public class CustomerLinkmanController extends BaseController {

    @Resource
    private CustomerLinkmanService customerLinkmanService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(CustomerLinkmanQuery customerLinkmanQuery) {
        return customerLinkmanService.queryCustomerLinkmanByCusId(customerLinkmanQuery);
    }

    @RequestMapping("/addOrUpdateCusLinkmanPage")
    public String addOrUpdateCusLinkmanPage(Integer cid, Integer id, Model model) {
        model.addAttribute("customerLinkman", customerLinkmanService.getById(id));
        model.addAttribute("cid", cid);
        return "/customerLinkman/add_update";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ResultInfo saveOrUpdate(CustomerLinkman customerLinkman) {
        customerLinkman.setIsValid(0);
        customerLinkmanService.saveOrUpdateCusLinkman(customerLinkman);
        return success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除的联系人记录!");
        customerLinkmanService.removeBatchByIds(Arrays.asList(ids));
        return success("联系人删除成功!");
    }
}
