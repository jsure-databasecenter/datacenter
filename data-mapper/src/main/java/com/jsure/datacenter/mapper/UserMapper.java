package com.jsure.datacenter.mapper;

import com.jsure.datacenter.entitymodel.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(int id);
}