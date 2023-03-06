package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName t_customer_loss
 */
@TableName(value ="t_customer_loss")
@Data
public class CustomerLoss implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "cus_no")
    private String cusNo;

    /**
     * 
     */
    @TableField(value = "cus_name")
    private String cusName;

    /**
     * 
     */
    @TableField(value = "cus_manager")
    private String cusManager;

    /**
     * 
     */
    @TableField(value = "last_order_time")
    private Date lastOrderTime;

    /**
     * 
     */
    @TableField(value = "confirm_loss_time")
    private Date confirmLossTime;

    /**
     * 
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 
     */
    @TableField(value = "loss_reason")
    private String lossReason;

    /**
     * 
     */
    @TableField(value = "is_valid")
    @TableLogic
    private Integer isValid;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}