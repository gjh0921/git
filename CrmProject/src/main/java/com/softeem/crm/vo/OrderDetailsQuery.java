package com.softeem.crm.vo;

import com.softeem.crm.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsQuery extends BaseQuery {
    private Integer orderId;
}
