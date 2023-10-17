package com.study.myshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author msl
 * @version 1.0
 * @create 2023-09-19 19:27
 *  //@SpringBootApplication(exclude= {SecurityAutoConfiguration.class })    //去除认证
 */
@SpringBootApplication
@MapperScan(value = "com.study.myshop.repository")
public class Application {
    public static void main(String[] args) {
        //调用运行方法(应用程序类, 系统参数)
        SpringApplication.run(Application.class, args);
    }
}
