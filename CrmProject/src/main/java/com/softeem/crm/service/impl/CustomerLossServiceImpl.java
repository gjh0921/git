package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.pojo.CustomerLoss;
import com.softeem.crm.service.CustomerLossService;
import com.softeem.crm.mapper.CustomerLossMapper;
import com.softeem.crm.vo.CustomerLossQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wangw
 * @description 针对表【t_customer_loss】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:59
 */
@Service
public class CustomerLossServiceImpl extends ServiceImpl<CustomerLossMapper, CustomerLoss>
        implements CustomerLossService {

    @Override
    public Map<String, Object> queryByParamsForTable(CustomerLossQuery customerLossQuery) {
        Page<CustomerLoss> page = new Page<>(customerLossQuery.getPage(), customerLossQuery.getLimit());
        LambdaQueryWrapper<CustomerLoss> queryWrapper = Wrappers.<CustomerLoss>lambdaQuery()
                .eq(StringUtils.isNotBlank(customerLossQuery.getCusNo()), CustomerLoss::getCusNo, customerLossQuery.getCusNo())
                .like(StringUtils.isNotBlank(customerLossQuery.getCusName()), CustomerLoss::getCusName, customerLossQuery.getCusName())
                .eq(customerLossQuery.getState() != null, CustomerLoss::getState, customerLossQuery.getState())
                .orderByDesc(CustomerLoss::getId);
        Page<CustomerLoss> customerLossPage = this.baseMapper.selectPage(page, queryWrapper);
        HashMap<String, Object> result = new HashMap<>();
        result.put("count", customerLossPage.getTotal());
        result.put("data", customerLossPage.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Override
    public void updateState(CustomerLoss customerLoss) {
        customerLoss.setState(1);
        customerLoss.setConfirmLossTime(new Date());
        baseMapper.updateById(customerLoss);
    }
}




