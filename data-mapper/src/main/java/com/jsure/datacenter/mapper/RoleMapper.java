package com.jsure.datacenter.mapper;

import com.jsure.datacenter.model.entitymodel.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    Role selectPermissionByRoleId(Integer roleId);
}