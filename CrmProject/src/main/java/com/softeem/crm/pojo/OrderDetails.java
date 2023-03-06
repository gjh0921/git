package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName t_order_details
 */
@TableName(value = "t_order_details")
@Data
public class OrderDetails implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "order_id")
    private Integer orderId;

    /**
     *
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     *
     */
    @TableField(value = "goods_num")
    private Integer goodsNum;

    /**
     *
     */
    @TableField(value = "unit")
    private String unit;

    /**
     *
     */
    @TableField(value = "price")
    private Double price;

    /**
     *
     */
    @TableField(value = "sum")
    private Double sum;

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