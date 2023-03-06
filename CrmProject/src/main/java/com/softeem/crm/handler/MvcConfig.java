package com.softeem.crm.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//配置拦截器
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    /* @Bean
     public NoLoginInterceptor noLoginInterceptor() {
         return new NoLoginInterceptor();
     }*/
    @Autowired
    private NoLoginInterceptor noLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noLoginInterceptor)
                //所有请求都会拦截
                .addPathPatterns("/**")
                //但是不包括以下资源
                .excludePathPatterns("/user/login", "/index", "/css/**", "/js/**", "/images/**", "/lib/**");
    }
}