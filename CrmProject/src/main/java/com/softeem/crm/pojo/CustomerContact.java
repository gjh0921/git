package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @TableName t_customer_contact
 */
@TableName(value = "t_customer_contact")
@Data
public class CustomerContact implements Serializable {
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
    @TableField(value = "contact_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date contactTime;

    /**
     *
     */
    @TableField(value = "address")
    private String address;

    /**
     *
     */
    @TableField(value = "overview")
    private String overview;

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