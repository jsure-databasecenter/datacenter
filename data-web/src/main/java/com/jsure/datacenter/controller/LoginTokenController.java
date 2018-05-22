package com.jsure.datacenter.controller;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.jsure.datacenter.constant.SystemConstant;
import com.jsure.datacenter.exception.SystemException;
import com.jsure.datacenter.model.enummodel.SystemErrorEnum;
import com.jsure.datacenter.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 登陆
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Map<String, Object> login(String userName, String password) {
        log.info("call login, {}, URI:{}", requestParamToString(request), request.getRequestURI());
        Map<String, Object> result = Maps.newHashMap();
        Subject subject = SecurityUtils.getSubject();
        boolean fa = subject.hasRole("admin");
        try {
            result = tokenService.login(userName,password);
            log.info("success to login, RESULT:{}", result);
            return successData(SystemConstant.SYS_LOGIN_SUCCESSF, result);
        } catch (SystemException jex) {
            log.error("failed to login, RESULT:{},cause:{}", result, jex);
            return failedData(jex.getCode(), jex.getMessage(), result);
        } catch (Exception e) {
            log.error("failed to login,RESULT:{}, cause:{}", result, Throwables.getStackTraceAsString(e));
            return failedData(SystemErrorEnum.ERROR_CODE_341FFF.getErrorCode(), SystemErrorEnum.ERROR_CODE_341FFF.getErrorDesc(), result);
        }
    }

}
