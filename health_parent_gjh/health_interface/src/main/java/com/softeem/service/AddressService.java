package com.softeem.service;

import com.softeem.entity.PageResult;
import com.softeem.entity.QueryPageBean;
import com.softeem.pojo.Address;
import com.softeem.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface AddressService {
    public List<Address> findAll();
    public PageResult findPage(QueryPageBean queryPageBean);
    Map findUpdate(Integer id);
    void add(Address address);
    void edit(Address address);
    void delete(Integer id);
}
