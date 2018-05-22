package com.jsure.datacenter.mapper;

import com.jsure.datacenter.model.entitymodel.TUser;
import org.springframework.stereotype.Component;

@Component
public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser findByUserName(String userName);
}