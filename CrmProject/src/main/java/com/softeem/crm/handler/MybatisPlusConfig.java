package com.softeem.crm.handler;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//mybatis的配置类
@Slf4j
@Configuration
//@MapperScan("com.softeem.crm.mapper")  //可以将主类中的注解移到此处
public class MybatisPlusConfig {
    //添加分页插件
    //<bean id='mybatisPlusInterceptor' class='com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor'/>
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());//乐观锁
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//分页
        return interceptor;
    }
}
