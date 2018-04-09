package com.jsure.datacenter.mapper;

import com.jsure.datacenter.entitymodel.User;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);
}