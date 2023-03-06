package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName t_customer
 */
@TableName(value = "t_customer")
@Data
public class Customer implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "khno")
    private String khno;

    /**
     *
     */
    @TableField(value = "name")
    private String name;

    /**
     *
     */
    @TableField(value = "area")
    private String area;

    /**
     *
     */
    @TableField(value = "cus_manager")
    private String cusManager;

    /**
     *
     */
    @TableField(value = "level")
    private String level;

    /**
     *
     */
    @TableField(value = "myd")
    private String myd;

    /**
     *
     */
    @TableField(value = "xyd")
    private String xyd;

    /**
     *
     */
    @TableField(value = "address")
    private String address;

    /**
     *
     */
    @TableField(value = "post_code")
    private String postCode;

    /**
     *
     */
    @TableField(value = "phone")
    private String phone;

    /**
     *
     */
    @TableField(value = "fax")
    private String fax;

    /**
     *
     */
    @TableField(value = "web_site")
    private String webSite;

    /**
     *
     */
    @TableField(value = "yyzzzch")
    private String yyzzzch;

    /**
     *
     */
    @TableField(value = "fr")
    private String fr;

    /**
     *
     */
    @TableField(value = "zczj")
    private String zczj;

    /**
     *
     */
    @TableField(value = "nyye")
    private String nyye;

    /**
     *
     */
    @TableField(value = "khyh")
    private String khyh;

    /**
     *
     */
    @TableField(value = "khzh")
    private String khzh;

    /**
     *
     */
    @TableField(value = "dsdjh")
    private String dsdjh;

    /**
     *
     */
    @TableField(value = "gsdjh")
    private String gsdjh;

    /**
     *
     */
    @TableField(value = "state")
    private Integer state;

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