package com.softeem.dao;

import com.github.pagehelper.Page;
import com.softeem.pojo.CheckGroup;
import com.softeem.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setSetmealAndCheckGroup(@Param("map") Map<String, Integer> map);
    public Page<Setmeal> selectByCondition(String queryString);
    List<Integer> findCheckGroupIdsBySetmealId(Integer id);
    void deleteAssociation(Integer id);
    void delete(Integer id);
    void updateSetmeal(Setmeal setmeal);
    public Setmeal findById(Integer id);
    public List<Setmeal> findAll();
    public Setmeal findSetmealById(Integer id);
    public List<Map<String,Object>> findSetmealCount();
}
