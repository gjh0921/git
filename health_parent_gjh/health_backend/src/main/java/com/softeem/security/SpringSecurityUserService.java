package com.softeem.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.softeem.pojo.Permission;
import com.softeem.pojo.Role;
import com.softeem.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService{
    @Reference //注意：此处要通过dubbo远程调用用户服务
    private UserService userService;
  
    //根据用户名查询用户信息
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      	//远程调用用户服务，根据用户名查询用户信息
        com.softeem.pojo.User user = userService.findByUsername(username);
        if(user == null){
              //用户名不存在
              return null;
        }
        //权限与角色保存到此List集合中
        List<GrantedAuthority> list = new ArrayList<>();
        //从用户中获取此用户的所有角色
        Set<Role> roles = user.getRoles();
        for(Role role : roles){
            //授予角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));
            //从角色中获取此角色的所有权限
            Set<Permission> permissions = role.getPermissions();
            for(Permission permission : permissions){
              	//授权
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        
        UserDetails userDetails = new User(username,user.getPassword(),list);
        return userDetails;
    }
}