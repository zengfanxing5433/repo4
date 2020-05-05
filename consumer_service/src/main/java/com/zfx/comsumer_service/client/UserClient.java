package com.zfx.comsumer_service.client;

import com.zfx.comsumer_service.client.impl.UserClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/** @description: feign的使用(使用springMVC的注解)
 *
 *  ==> 告诉feign四个参数, feign会根据注解帮我们生成URL
 *
 *  ==> 熔断配置: fallback=接口的实现类
 */
@FeignClient(value = "user-service", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("user/{id}")
    String findById(@PathVariable("id") Integer id);

}
