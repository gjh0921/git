package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.pojo.UserRole;
import com.softeem.crm.service.UserRoleService;
import com.softeem.crm.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author wangw
* @description 针对表【t_user_role】的数据库操作Service实现
* @createDate 2022-12-27 14:22:59
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




