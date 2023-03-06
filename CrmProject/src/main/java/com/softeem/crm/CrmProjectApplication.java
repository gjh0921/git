package com.softeem.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.softeem.crm.mapper")
@EnableCaching//开启定时任务
public class CrmProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmProjectApplication.class, args);
    }

}
