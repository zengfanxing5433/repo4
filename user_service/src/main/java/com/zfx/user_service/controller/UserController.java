package com.zfx.user_service.controller;

import com.zfx.user_service.pojo.User;
import com.zfx.user_service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zfx
 * @version: 1.0
 * @time: 2020-05-03 15:40
 * <p>
 * 修改历史：
 * 修改日期      修改人员    版本    内容
 * 2020-05-03   zfx       1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }
}
