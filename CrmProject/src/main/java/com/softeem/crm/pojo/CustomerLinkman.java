package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName t_customer_linkman
 */
@TableName(value = "t_customer_linkman")
@Data
public class CustomerLinkman implements Serializable {
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
    @TableField(value = "link_name")
    private String linkName;

    /**
     *
     */
    @TableField(value = "sex")
    private String sex;

    /**
     *
     */
    @TableField(value = "zhiwei")
    private String zhiwei;

    /**
     *
     */
    @TableField(value = "office_phone")
    private String officePhone;

    /**
     *
     */
    @TableField(value = "phone")
    private String phone;

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