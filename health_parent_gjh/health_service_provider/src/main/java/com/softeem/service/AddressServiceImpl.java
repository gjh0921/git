package com.softeem.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.softeem.constant.RedisConstant;
import com.softeem.dao.AddressDao;
import com.softeem.entity.PageResult;
import com.softeem.entity.QueryPageBean;
import com.softeem.pojo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = AddressService.class)
@Transactional
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressDao addressDao;
    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Page<Address> page = addressDao.findPage(queryPageBean.getQueryString());
       return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Map findUpdate(Integer id) {
        Address address=addressDao.findUpdate(id);
        Map map=new HashMap<>();
        map.put("address",address);
        return map;
    }

    @Override
    public void add(Address address) {
        addressDao.add(address);
    }

    @Override
    public void edit(Address address) {
        addressDao.edit(address);
    }

    @Override
    public void delete(Integer id) {
        addressDao.delete(id);
    }
}
