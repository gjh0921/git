package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.mapper.SaleChanceMapper;
import com.softeem.crm.pojo.CusDevPlan;
import com.softeem.crm.service.CusDevPlanService;
import com.softeem.crm.mapper.CusDevPlanMapper;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.CusDevPlanQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_cus_dev_plan】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:59
 */
@Service
public class CusDevPlanServiceImpl extends ServiceImpl<CusDevPlanMapper, CusDevPlan>
        implements CusDevPlanService {
    @Resource
    private SaleChanceMapper saleChanceMapper;

    @Override
    public Map<String, Object> queryByParamsForTable(CusDevPlanQuery cusDevPlanQuery) {
        Page<CusDevPlan> page = new Page<>(cusDevPlanQuery.getPage(), cusDevPlanQuery.getLimit());
        LambdaQueryWrapper<CusDevPlan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CusDevPlan::getSaleChanceId, cusDevPlanQuery.getSid())
                .orderByDesc(CusDevPlan::getId);
        Page<CusDevPlan> cusDevPlanPage = this.baseMapper.selectPage(page, queryWrapper);
        HashMap<String, Object> result = new HashMap<>();
        result.put("count", cusDevPlanPage.getTotal());
        result.put("data", cusDevPlanPage.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Transactional//事务注解
    public void saveOrUpdateCusDevPlan(CusDevPlan cusDevPlan) {
        checkParams(cusDevPlan.getSaleChanceId(), cusDevPlan.getPlanItem(), cusDevPlan.getPlanDate());
        cusDevPlan.setIsValid(0);//0是有效数据
        AssertUtil.isTrue(!this.saveOrUpdate(cusDevPlan), "计划项记录操作失败!");
    }

    private void checkParams(Integer saleChanceId, String planItem, Date planDate) {
        AssertUtil.isTrue(null == saleChanceId || null == saleChanceMapper.selectById(saleChanceId), "请设置营销机会id");
        AssertUtil.isTrue(StringUtils.isBlank(planItem), "请输入计划项内容!");
        AssertUtil.isTrue(null == planDate, "请指定计划项日期!");
    }
}




