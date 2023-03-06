package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName t_customer_order
 */
@TableName(value = "t_customer_order")
@Data
public class CustomerOrder implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "cus_id")
    private Integer cusId;

    /**
     *
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     *
     */
    @TableField(value = "order_date")
    private Date orderDate;

    /**
     *
     */
    @TableField(value = "address")
    private String address;

    /**
     *
     */
    @TableField(value = "state")
    private Integer state;

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
    @TableField(value = "is_valid")
    @TableLogic
    private Integer isValid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}