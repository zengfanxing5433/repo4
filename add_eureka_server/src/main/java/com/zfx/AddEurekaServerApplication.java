package com.zfx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description:
 * @author: zfx
 * @version: 1.0
 * @time: 2020-05-03 17:22
 * <p>
 * 修改历史：
 * 修改日期      修改人员    版本    内容
 * 2020-05-03   zfx       1.0
 */
@EnableEurekaServer     //启用Eureka服务
@SpringBootApplication
public class AddEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddEurekaServerApplication.class);
    }
}
