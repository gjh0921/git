package com.softeem.crm.mapper;

import com.softeem.crm.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_role】的数据库操作Mapper
 * @createDate 2022-12-27 14:22:59
 * @Entity com.softeem.crm.pojo.Role
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Map> queryAllRoles(Integer userId);
}




