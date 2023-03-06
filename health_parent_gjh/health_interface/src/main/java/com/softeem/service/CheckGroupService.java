package com.softeem.service;

import com.softeem.entity.PageResult;
import com.softeem.entity.QueryPageBean;
import com.softeem.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    void add(CheckGroup checkGroup, Integer[] checkitemIds);
    void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds);
    public PageResult pageQuery(QueryPageBean queryPageBean);
    CheckGroup findById(Integer id);
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    public void edit(CheckGroup checkGroup,Integer[] checkitemIds);
    void delete(Integer id);
    public List<CheckGroup> findAll();
}
