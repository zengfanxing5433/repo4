package com.zfx.user_service.service.impl;

import com.zfx.user_service.mapper.IUserMapper;
import com.zfx.user_service.pojo.User;
import com.zfx.user_service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zfx
 * @version: 1.0
 * @time: 2020-05-03 15:35
 * <p>
 * 修改历史：
 * 修改日期      修改人员    版本    内容
 * 2020-05-03   zfx       1.0
 */
@Service
public class UserMapperImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public User findById(Integer id) {
        //Thread.sleep(20000L);  //模拟超时
        return userMapper.selectByPrimaryKey(id);
    }
}
