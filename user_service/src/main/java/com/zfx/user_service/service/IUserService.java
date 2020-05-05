package com.zfx.user_service.service;

import com.zfx.user_service.pojo.User;

/**
 * @description:
 * @author: zfx
 * @version: 1.0
 * @time: 2020-05-03 15:34
 * <p>
 * 修改历史：
 * 修改日期      修改人员    版本    内容
 * 2020-05-03   zfx       1.0
 */
public interface IUserService {

    public User findById(Integer id);
}
