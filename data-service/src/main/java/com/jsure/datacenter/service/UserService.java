package com.jsure.datacenter.service;

import com.jsure.datacenter.model.resultmodel.UserResut;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/8
 * @Time: 16:05
 * I am a Code Man -_-!
 */
public interface UserService {

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    UserResut queryUserInfo(Integer id);

    /**
     * 查询用户对应的角色
     *
     * @param id
     * @return
     */
    Integer queryUserforRoleId(Integer id);
}
