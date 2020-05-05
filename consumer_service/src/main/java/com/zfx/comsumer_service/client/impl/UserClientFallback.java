package com.zfx.comsumer_service.client.impl;

import com.zfx.comsumer_service.client.UserClient;
import org.springframework.stereotype.Component;

/**
 * @description:  写熔断的业务逻辑
 */
@Component
public class UserClientFallback implements UserClient {

    @Override
    public String findById(Integer id) {
        return "未知用户!";
    }
}
