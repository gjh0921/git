package com.softeem.crm.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.softeem.crm.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CusDevPlanQuery extends BaseQuery {
    // 营销机会id
    private Integer sid;
}