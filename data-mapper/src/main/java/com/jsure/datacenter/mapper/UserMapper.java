package com.jsure.datacenter.mapper;

import com.jsure.datacenter.model.entitymodel.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(int id);

    Integer selectRoleIdByUserId(Integer id);
}