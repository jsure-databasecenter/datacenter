package com.jsure.datacenter.service;

import com.jsure.datacenter.resultmodel.RoleResult;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/10
 * @Time: 15:39
 * I am a Code Man -_-!
 */
public interface PermissionService {

    RoleResult queryPermission(Integer roleId);
}
