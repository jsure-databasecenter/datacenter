package com.jsure.datacenter.service;

import com.jsure.datacenter.model.entitymodel.TUser;

import java.util.Map;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/5/21
 * @Time: 15:07
 * I am a Code Man -_-!
 */
public interface TokenService {

    Map<String, Object> login(String userName, String password);

    TUser findUserByUserName(String userName);
}
