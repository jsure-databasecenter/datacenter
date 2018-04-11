package com.jsure.datacenter.serviceImpl;

import com.jsure.datacenter.mapper.RoleMapper;
import com.jsure.datacenter.model.entitymodel.Role;
import com.jsure.datacenter.model.resultmodel.RoleResult;
import com.jsure.datacenter.service.RoleService;
import com.jsure.datacenter.utils.BeanMapper;
import com.jsure.datacenter.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/10
 * @Time: 15:44
 * I am a Code Man -_-!
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleResult queryRoleInfomation(Integer roleId) {
        Role role = roleMapper.selectPermissionByRoleId(roleId);
        RoleResult result = new RoleResult();
        BeanMapper.copy(role, result);
        return result;
    }

    @Override
    public String[] queryPermission(Integer roleId) {
        Role role = roleMapper.selectPermissionByRoleId(roleId);
        //数据判空
        if (ObjectUtils.isNullOrEmpty(role)) {
            return null;
        }
        RoleResult result = new RoleResult();
        BeanMapper.copy(role, result);
        //取出所有权限
        String businesspermission = result.getBusinesspermissionstring();
        String[] permission = null;
        //将权限字符串拆分成数组
        if (ObjectUtils.isNotNullAndEmpty(businesspermission)) {
            permission = businesspermission.split(",");
        }
        //返回角色权限数组
        return permission;
    }

}
