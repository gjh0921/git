package com.softeem.crm.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softeem.crm.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerQuery extends BaseQuery {
    private String cusName;

    private String cusNo;

    private String level;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    // 1  0-1000   2 1000-3000  3  3000-5000  4 50000
    private String type;
}