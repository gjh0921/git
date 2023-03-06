package com.softeem.crm.handler;

import com.softeem.crm.exceptions.NoAuthException;
import com.softeem.crm.exceptions.NoLoginException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component//组件，被spring容器收纳
@Aspect//aop
public class PermissionProxy {

    @Autowired
    private HttpSession session;

    //环绕通知
    //环绕通知的触发条件是方法上面加入了@RequirePermission注解
    @Around(value = "@annotation(com.softeem.crm.handler.RequirePermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        if (null == permissions || permissions.size() == 0) {
            throw new NoAuthException();
        }
        Object result = null;
        //获取要请求的方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();//得到方法声明
        //得到方法上的注解
        RequirePermission requirePermission = methodSignature.getMethod().getDeclaredAnnotation(RequirePermission.class);
        if (!(permissions.contains(requirePermission.code()))) {
            throw new NoAuthException();
        }
        result = pjp.proceed();//方法继续
        return result;
    }
}