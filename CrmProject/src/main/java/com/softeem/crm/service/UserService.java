package com.softeem.crm.service;

import com.softeem.crm.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.softeem.crm.vo.UserModel;
import com.softeem.crm.vo.UserQuery;

import java.util.List;
import java.util.Map;

/**
 * @author wangw
 * @description 针对表【t_user】的数据库操作Service
 * @createDate 2022-12-27 14:22:59
 */
public interface UserService extends IService<User> {
    public UserModel login(String userName, String userPwd);

    public void updateUserPassword(Integer userId, String oldPassword, String newPassword, String confirmPassword);

    public List<User> queryAllSales();

    Map<String, Object> userList(UserQuery userQuery);

    public void saveUser(User user,String roleIds);

    public void updateUser(User user,String roleIds);

    public void deleteUserByIds(Integer[] ids);
}
