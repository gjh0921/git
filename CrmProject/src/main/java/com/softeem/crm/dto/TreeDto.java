package com.softeem.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

//数据传输对象
public class TreeDto implements Serializable {
    private Integer id;
    private Integer pId;
    private String name;
    private Boolean checked;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
