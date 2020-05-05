package com.zfx.comsumer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: zfx
 * @version: 1.0
 * @time: 2020-05-03 15:55
 * <p>
 * 修改历史：
 * 修改日期      修改人员    版本    内容
 * 2020-05-03   zfx       1.0
 */

//@EnableHystrix  //启动服务降级

//@EnableCircuitBreaker   //启动服务熔断(包含上面的降级)
//@EnableDiscoveryClient
//@SpringBootApplication

@SpringCloudApplication  //包含上述3行注释(cloud必用的3行)
@EnableFeignClients     //启动feign
public class ConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerServiceApplication.class, args);
    }

    //开启了Fegin下面的代码也不需要了
    @Bean
    @LoadBalanced   //内置拦截器, 拦截REST风格的请求
    public RestTemplate getRestTempalte() {
        return new RestTemplate();
    }

}
