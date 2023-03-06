package com.softeem.crm.service;

import com.softeem.crm.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.vo.RoleQuery;

import java.util.List;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_role】的数据库操作Service
 * @createDate 2022-12-27 14:22:59
 */
public interface RoleService extends IService<Role> {
    public List<Map> queryAllRoles(Integer userId);

    Map<String, Object> queryByParamsForTable(RoleQuery roleQuery);

    public void saveRole(Role role);

    public void updateRole(Role role);

    public void addGrant(Integer[] mids, Integer roleId);
}
