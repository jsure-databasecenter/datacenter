package com.jsure.datacenter.controller;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.jsure.datacenter.model.enummodel.JsureErrorEnum;
import com.jsure.datacenter.exception.JsureException;
import com.jsure.datacenter.service.RoleService;
import com.jsure.datacenter.service.UserService;
import com.jsure.datacenter.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/8
 * @Time: 15:04
 * I am a Code Man -_-!
 */
@Slf4j
@RestController
@RequestMapping(value = "api/")
@Api(description = "登录注册")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "登录", notes = "根据token和用户id来获取用户权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "token", value = "token凭证", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "account/login", method = RequestMethod.POST)
    public String login(@RequestParam Integer userId, @RequestParam String token, HttpSession session) {
        log.info("call login, parameter:{}", userId, token);
        Map<String, Object> resultMap = Maps.newHashMap();
        try {
            //获取用户角色id
            Integer roleId = userService.queryUserforRoleId(userId);
            //获取用户角色权限
            String[] permission = roleService.queryPermission(roleId);
            //将token存入Session
            session.setAttribute("userToken", token);
            //将permission存入Session
            session.setAttribute("permission", permission);
            resultMap.put("permission", permission);
            log.info("success to login, RESULT:{}", resultMap);
        } catch (JsureException jex) {
            resultMap.put("errorCode", jex.getCode());
            resultMap.put("errorMsg", jex.getMessage());
            log.error("failed to login, RESULT:{},cause:{}", resultMap, jex);
        } catch (Exception e) {
            resultMap.put("errorCode", JsureErrorEnum.ERROR_CODE_341FFF.getErrorCode());
            resultMap.put("errorMsg", JsureErrorEnum.ERROR_CODE_341FFF.getErrorDesc());
            log.error("failed to login,RESULT:{}, cause:{}", resultMap, Throwables.getStackTraceAsString(e));
        }
        return JsonUtil.obj2json(resultMap);
    }
}
