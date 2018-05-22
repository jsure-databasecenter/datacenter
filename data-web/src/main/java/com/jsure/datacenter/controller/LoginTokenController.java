package com.jsure.datacenter.controller;

import com.google.common.collect.Maps;
import com.jsure.datacenter.constant.SystemConstant;
import com.jsure.datacenter.model.entitymodel.TUser;
import com.jsure.datacenter.model.enummodel.SystemErrorEnum;
import com.jsure.datacenter.service.TokenService;
import com.jsure.datacenter.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/5/21
 * @Time: 14:49
 * I am a Code Man -_-!
 */
@Slf4j
@RestController
@RequestMapping(value = "api/user/")
public class LoginTokenController extends BaseController {

    @Autowired
    private TokenService tokenService;

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map<String, Object> login(String userName, String password) {
        log.info("call login, {}, URI:{}", requestParamToString(request), request.getRequestURI());
        Map<String, Object> result = Maps.newHashMap();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken shiroToken = new UsernamePasswordToken(userName, password);
        try {
            subject.login(shiroToken);
            TUser user = (TUser) subject.getPrincipal();
            String token = JwtUtils.createJWT(user.getId().toString(), user.getUserName(), SystemConstant.JWT_TTL);
            result.put("Authorization", token);
            log.info("success to login, RESULT:{}", result);
            return successData(SystemConstant.SYS_LOGIN_SUCCESSF, result);
        } catch (UnknownAccountException e) {
            log.error("failed to login, RESULT:{},cause:{}", result, e);
            return failedData(SystemErrorEnum.ERROR_CODE_341005.getErrorCode(), SystemErrorEnum.ERROR_CODE_341005.getErrorDesc(), result);
        } catch (IncorrectCredentialsException e) {
            log.error("failed to login, RESULT:{},cause:{}", result, e);
            return failedData(SystemErrorEnum.ERROR_CODE_341006.getErrorCode(), SystemErrorEnum.ERROR_CODE_341006.getErrorDesc(), result);
        } catch (AuthenticationException e) {
            log.error("failed to login, RESULT:{},cause:{}", result, e);
            return failedData(SystemErrorEnum.ERROR_CODE_341007.getErrorCode(), SystemErrorEnum.ERROR_CODE_341007.getErrorDesc(), result);
        } catch (Exception e) {
            log.error("failed to login, RESULT:{},cause:{}", result, e);
            return failedData(SystemErrorEnum.ERROR_CODE_341FFF.getErrorCode(), SystemErrorEnum.ERROR_CODE_341FFF.getErrorDesc(), result);
        }
    }

}
