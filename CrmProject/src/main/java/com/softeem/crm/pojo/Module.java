package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName t_module
 */
@TableName(value = "t_module")
@Data
public class Module implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源名称
     */
    @TableField(value = "module_name")
    private String moduleName;

    /**
     * 模块样式
     */
    @TableField(value = "module_style")
    private String moduleStyle;

    /**
     * 地址
     */
    @TableField(value = "url")
    private String url;

    /**
     *
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     *
     */
    @TableField(value = "parent_opt_value")
    private String parentOptValue;

    /**
     * 等级
     */
    @TableField(value = "grade")
    private Integer grade;

    /**
     * 权限值
     */
    @TableField(value = "opt_value")
    private String optValue;

    /**
     *
     */
    @TableField(value = "orders")
    private Integer orders;

    /**
     *
     */
    @TableField(value = "is_valid", fill = FieldFill.INSERT)
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