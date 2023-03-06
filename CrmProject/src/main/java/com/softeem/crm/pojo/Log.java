package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_log
 */
@TableName(value ="t_log")
@Data
public class Log implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "description")
    private String description;

    /**
     * 
     */
    @TableField(value = "method")
    private String method;

    /**
     * 
     */
    @TableField(value = "type")
    private String type;

    /**
     * 
     */
    @TableField(value = "request_ip")
    private String requestIp;

    /**
     * 
     */
    @TableField(value = "exception_code")
    private String exceptionCode;

    /**
     * 
     */
    @TableField(value = "exception_detail")
    private String exceptionDetail;

    /**
     * 
     */
    @TableField(value = "params")
    private String params;

    /**
     * 
     */
    @TableField(value = "create_date")
    private Date createDate;

    /**
     * 
     */
    @TableField(value = "execute_time")
    private Integer executeTime;

    /**
     * 
     */
    @TableField(value = "create_man")
    private String createMan;

    /**
     * 
     */
    @TableField(value = "result")
    private String result;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}