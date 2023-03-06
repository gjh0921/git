package com.softeem.crm.vo;

import com.softeem.crm.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery extends BaseQuery {
    // 用户名
    private String userName;
    // 邮箱
    private String email;
    // 电话
    private String phone;
    /*
      省略get |set 方法
    */
}