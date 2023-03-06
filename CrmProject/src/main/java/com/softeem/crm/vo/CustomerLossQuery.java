package com.softeem.crm.vo;

import com.softeem.crm.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLossQuery extends BaseQuery {
    private String cusNo;
    private String CusName;
    private Integer state;
}
