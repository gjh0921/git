package com.softeem.service;


import com.softeem.entity.PageResult;
import com.softeem.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    public void saveOrUpdate(Setmeal setmeal, Integer[] checkgroupIds);
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    void delete(Integer id);
    Map findById(Integer id);
    public List<Setmeal> findAll();
    Setmeal findBySetmealId(Integer id);
    void generateMobileStaticHtml();
    public List<Map<String,Object>> findSetmealCount();
}
