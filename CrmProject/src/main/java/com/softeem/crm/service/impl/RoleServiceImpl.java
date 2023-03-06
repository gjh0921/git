package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.mapper.ModuleMapper;
import com.softeem.crm.mapper.PermissionMapper;
import com.softeem.crm.pojo.Permission;
import com.softeem.crm.pojo.Role;
import com.softeem.crm.service.PermissionService;
import com.softeem.crm.service.RoleService;
import com.softeem.crm.mapper.RoleMapper;
import com.softeem.crm.utils.AssertUtil;
import com.softeem.crm.vo.RoleQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_role】的数据库操作Service实现
 * @createDate 2022-12-27 14:22:59
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private PermissionService permissionService;

    @Override
    public List<Map> queryAllRoles(Integer userId) {
        return this.baseMapper.queryAllRoles(userId);
    }

    @Override
    public Map<String, Object> queryByParamsForTable(RoleQuery roleQuery) {
        Page<Role> page = new Page<>(roleQuery.getPage(), roleQuery.getLimit());
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(roleQuery.getRoleName()), Role::getRoleName, roleQuery.getRoleName())
                .orderByDesc(Role::getId);
        Page<Role> rolePage = this.baseMapper.selectPage(page, queryWrapper);
        HashMap<String, Object> result = new HashMap<>();
        result.put("count", rolePage.getTotal());
        result.put("data", rolePage.getRecords());
        result.put("code", 0);
        result.put("msg", "");
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "请输入角色名!");
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getRoleName, role.getRoleName());
        Role temp = this.baseMapper.selectOne(queryWrapper);
        AssertUtil.isTrue(null != temp, "该角色已存在!");
        role.setIsValid(0);
        AssertUtil.isTrue(!this.save(role), "角色记录添加失败!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateRole(Role role) {
        AssertUtil.isTrue(null == role.getId() || null == this.getById(role.getId()), "待修改的记录不存在!");
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()), "请输入角色名!");
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getRoleName, role.getRoleName());
        Role temp = this.baseMapper.selectOne(queryWrapper);
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(role.getId())), "该角色已存在!");
        AssertUtil.isTrue(!this.updateById(role), "角色记录更新失败!");
    }

    public void addGrant(Integer[] mids, Integer roleId) {
        /**
         * 核心表-t_permission  t_role(校验角色存在)
         *   如果角色存在原始权限  删除角色原始权限
         *     然后添加角色新的权限 批量添加权限记录到t_permission
         */
        Role temp = getById(roleId);
        AssertUtil.isTrue(null == roleId || null == temp, "待授权的角色不存在!");
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getRoleId, roleId);
        int count = permissionMapper.selectCount(queryWrapper).intValue();
        if (count > 0) {
            AssertUtil.isTrue(permissionMapper.delete(queryWrapper) < count, "权限分配失败!");
        }
        if (null != mids && mids.length > 0) {
            List<Permission> permissions = new ArrayList<Permission>();
            for (Integer mid : mids) {
                Permission permission = new Permission();

                permission.setModuleId(mid);
                permission.setRoleId(roleId);
                permission.setAclValue(moduleMapper.selectById(mid).getOptValue());
                permissions.add(permission);
            }
            permissionService.saveBatch(permissions);
        }
    }
}




