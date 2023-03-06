package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @TableName t_cus_dev_plan
 */
@TableName(value = "t_cus_dev_plan")
@Data
public class CusDevPlan implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "sale_chance_id")
    private Integer saleChanceId;

    /**
     *
     */
    @TableField(value = "plan_item")
    private String planItem;

    /**
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "plan_date")
    private Date planDate;

    /**
     *
     */
    @TableField(value = "exe_affect")
    private String exeAffect;

    /**
     *
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private Date createDate;

    /**
     *
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    /**
     *
     */
    @TableLogic//逻辑删除
    @TableField(value = "is_valid")
    private Integer isValid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}