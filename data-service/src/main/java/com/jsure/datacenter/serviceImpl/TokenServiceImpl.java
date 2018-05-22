package com.jsure.datacenter.serviceImpl;

import com.google.common.collect.Maps;
import com.jsure.datacenter.constant.SystemConstant;
import com.jsure.datacenter.exception.SystemException;
import com.jsure.datacenter.mapper.TUserMapper;
import com.jsure.datacenter.model.entitymodel.TUser;
import com.jsure.datacenter.model.enummodel.SystemErrorEnum;
import com.jsure.datacenter.service.TokenService;
import com.jsure.datacenter.utils.JwtUtils;
import com.jsure.datacenter.utils.MD5Util;
import com.jsure.datacenter.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/5/21
 * @Time: 15:09
 * I am a Code Man -_-!
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TUserMapper tUserMapper;

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Map<String, Object> login(String userName, String password) {
        Map<String, Object> resultMap = Maps.newHashMap();
        TUser user = tUserMapper.findByUserName(userName);
        //返回用户名错误
        if (ObjectUtils.isNullOrEmpty(user)) {
            throw new SystemException(SystemErrorEnum.ERROR_CODE_341005.getErrorCode(),
                    SystemErrorEnum.ERROR_CODE_341005.getErrorDesc());
        }
        //对密码加密
        password = MD5Util.MD5Encrypt(password);
        //返回密码错误
        if (!password.equals(user.getPassword())) {
            throw new SystemException(SystemErrorEnum.ERROR_CODE_341006.getErrorCode(),
                    SystemErrorEnum.ERROR_CODE_341006.getErrorDesc());
        } else {
            //把token返回给客户端-->客户端保存至cookie-->客户端每次请求附带cookie参数
            String token = JwtUtils.createJWT(user.getId().toString(), user.getUserName(), SystemConstant.JWT_TTL);
            resultMap.put("Authorization", token);
        }
        return resultMap;
    }

    @Override
    public TUser findUserByUserName(String userName) {
        return tUserMapper.findByUserName(userName);
    }
}
