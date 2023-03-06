package com.softeem.dao;

import com.github.pagehelper.Page;
import com.softeem.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);
    void setCheckGroupAndCheckItem(Map map);
    public Page<CheckGroup> selectByCondition(String queryString);
    public CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    void deleteAssociation(Integer id);
    void edit(CheckGroup checkGroup);
    void delete(Integer id);
    List<CheckGroup> findAll();
    List<CheckGroup> findCheckGroupById(@Param("id") Integer setmealId);

}
