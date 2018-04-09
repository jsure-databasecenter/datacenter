package com.jsure.datacenter.serviceImpl;

import com.jsure.datacenter.entitymodel.User;
import com.jsure.datacenter.mapper.UserMapper;
import com.jsure.datacenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/8
 * @Time: 16:07
 * I am a Code Man -_-!
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUsers(int id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
