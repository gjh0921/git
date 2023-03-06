package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName t_user
 */
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_user")
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     *
     */
    @TableField(value = "user_pwd")
    private String userPwd;

    /**
     *
     */
    @TableField(value = "true_name")
    private String trueName;

    /**
     *
     */
    @TableField(value = "email")
    private String email;

    /**
     *
     */
    @TableField(value = "phone")
    private String phone;

    /**
     *
     */
    @TableField(value = "is_valid")
    @TableLogic//逻辑删除
    private Integer isValid;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}