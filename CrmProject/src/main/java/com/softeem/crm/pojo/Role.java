package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName t_role
 */
@TableName(value ="t_role")
@Data
public class Role implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 备注
     */
    @TableField(value = "role_remark")
    private String roleRemark;

    /**
     * 
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     *
     */
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
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