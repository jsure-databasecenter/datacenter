package com.jsure.datacenter.service;


import com.jsure.datacenter.model.resultmodel.RoleResult;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/10
 * @Time: 15:39
 * I am a Code Man -_-!
 */
public interface RoleService {

    /**
     * 获取用户绑定的角色信息
     * @param roleId
     * @return
     */
    RoleResult queryRoleInfomation(Integer roleId);

    /**
     * 获取角色权限
     * @param roleId
     * @return
     */
    String[] queryPermission(Integer roleId);
}
