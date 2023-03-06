package com.softeem.dao;

import com.github.pagehelper.Page;
import com.softeem.pojo.Address;

import java.util.List;

public interface AddressDao {
    List<Address>  findAll();
    Page<Address> findPage(String queryString);
    void add(Address address);
    void edit(Address address);
    void delete(Integer id);
    Address findUpdate(Integer integer);
}
