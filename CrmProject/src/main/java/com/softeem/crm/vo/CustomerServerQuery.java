package com.softeem.crm.vo;

import com.softeem.crm.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServerQuery extends BaseQuery {
    private String customer;
    private String type;
    private Integer assigner;
    private String state;
}
