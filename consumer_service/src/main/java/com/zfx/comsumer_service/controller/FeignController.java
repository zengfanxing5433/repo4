package com.zfx.comsumer_service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zfx.comsumer_service.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @description: 存在问题 ==> "http://user-service/user/" + id;
     *    @http://user-service/ ==> 一般不会变
     *    @user/ ==> 写死了 ===>  feign
     *    @id ==> 一般不会变
     *
     * @写一个请求方法:  1. 请求的路径  http://user-service/user/
     *                 2. 请求的参数  id
     *                 3. 请求的方式  @GetMapping("/{id}")
     *                 4. 返回的结果  String.class
     *
     * @Fegin也需要上述4个参数 (写接口[类似mybatis], 不需要配置)
     */
//    @GetMapping("/{id}")
//    @HystrixCommand
//    public String findById(@PathVariable("id") Integer id) {
//        String url = "http://user-service/user/" + id;
//        return restTemplate.getForObject(url, String.class);
//    }

    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    public String findById(@PathVariable("id") Integer id) {
        System.out.println("feign");
        return userClient.findById(id);
    }
}
