package com.zfx.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

//@EnableEurekaClient  只能注册到eureka上(写死)
@EnableDiscoveryClient  //启动客户端, 推荐使用, 可以注册到各种注册中心
@SpringBootApplication
@MapperScan("com.zfx.user_service.mapper")
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
