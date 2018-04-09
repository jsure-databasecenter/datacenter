package com.jsure.datacenter.mapper;

import com.jsure.datacenter.entitymodel.User;
import org.springframework.stereotype.Component;


@Component
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}