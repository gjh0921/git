package com.softeem.crm.vo;

import com.softeem.crm.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLinkmanQuery extends BaseQuery {
    private Integer cusId;
}
