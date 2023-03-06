package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName t_datadic
 */
@TableName(value ="t_datadic")
@Data
public class Datadic implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "data_dic_name")
    private String dataDicName;

    /**
     * 
     */
    @TableField(value = "data_dic_value")
    private String dataDicValue;

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