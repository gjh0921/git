package com.softeem.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.softeem.dao.PermissionDao;
import com.softeem.dao.RoleDao;
import com.softeem.dao.UserDao;
import com.softeem.pojo.Permission;
import com.softeem.pojo.Role;
import com.softeem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    public User findByUsername(String username) {
        //通过用户名查询此用户信息保存到user对象
        User user = userDao.findByUsername(username);
        if(user == null){
            return null;
        }
        //通过用户的主键id查询出此用户的所有角色信息
        Integer userId = user.getId();
        Set<Role> roles = roleDao.findByUserId(userId);
        if(roles != null && roles.size() > 0){
            //循环角色信息，将每个角色的权限查询出来
            for(Role role : roles){
                //通过角色的主键id，将此角色的所有权限查询出来
                Integer roleId = role.getId();
                Set<Permission> permissions = permissionDao.findByRoleId(roleId);
                if(permissions != null && permissions.size() > 0){
                    //将此角色的权限保存到此角色role对象中
                    role.setPermissions(permissions);
                }
            }
             //将此用户的角色信息保存到user对象中
            user.setRoles(roles);
        }
        return user;
    }
}