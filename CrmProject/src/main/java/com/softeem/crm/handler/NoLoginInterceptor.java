package com.softeem.crm.handler;

import com.softeem.crm.exceptions.NoLoginException;
import com.softeem.crm.service.UserService;
import com.softeem.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//拦截器
@Component
public class NoLoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 获取cookie  解析用户id
         *    如果用户id存在 并且 数据库存在对应用户记录  放行  否则进行拦截 重定向到登录
         */
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        if(userId==0 || null==userService.getById(userId)){
            throw  new NoLoginException();
        }
        return true;
    }
}