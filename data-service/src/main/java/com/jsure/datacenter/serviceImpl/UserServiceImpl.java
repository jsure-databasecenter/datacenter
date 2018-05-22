package com.jsure.datacenter.serviceImpl;

import com.jsure.datacenter.model.entitymodel.User;
import com.jsure.datacenter.model.enummodel.SystemErrorEnum;
import com.jsure.datacenter.exception.SystemException;
import com.jsure.datacenter.mapper.UserMapper;
import com.jsure.datacenter.model.resultmodel.UserResut;
import com.jsure.datacenter.service.UserService;
import com.jsure.datacenter.utils.BeanMapper;
import com.jsure.datacenter.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/8
 * @Time: 16:07
 * I am a Code Man -_-!
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResut queryUserInfo(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        //如果没有查询到数据
        if(ObjectUtils.isNullOrEmpty(user)){
            throw new SystemException(SystemErrorEnum.ERROR_CODE_341004.getErrorCode(),
                    SystemErrorEnum.ERROR_CODE_341004.getErrorDesc());
        }
        UserResut result = new UserResut();
        BeanMapper.copy(user, result);
        return result;
    }

    @Override
    public Integer queryUserforRoleId(Integer id) {
        Integer roleId = userMapper.selectRoleIdByUserId(id);
        //如果没有查询到数据
        if(ObjectUtils.isNullOrEmpty(roleId)){
            throw new SystemException(SystemErrorEnum.ERROR_CODE_341003.getErrorCode(),
                    SystemErrorEnum.ERROR_CODE_341003.getErrorDesc());
        }
        return roleId;
    }
}
