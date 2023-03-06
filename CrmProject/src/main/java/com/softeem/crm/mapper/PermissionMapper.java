package com.softeem.crm.mapper;

import com.softeem.crm.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author wangw
 * @description 针对表【t_permission】的数据库操作Mapper
 * @createDate 2022-12-27 14:22:59
 * @Entity com.softeem.crm.pojo.Permission
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> queryUserHasRolesHasPermissions(int userId);
}




