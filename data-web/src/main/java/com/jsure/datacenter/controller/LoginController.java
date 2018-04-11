package com.jsure.datacenter.controller;

import com.google.common.base.Throwables;
import com.jsure.datacenter.entitymodel.User;
import com.jsure.datacenter.exception.JsureException;
import com.jsure.datacenter.resultmodel.RoleResult;
import com.jsure.datacenter.service.PermissionService;
import com.jsure.datacenter.service.UserService;
import com.jsure.datacenter.utils.JsonUtils;
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
    private PermissionService permissionService;

    @ApiOperation(value="登录", notes="根据token和用户id来获取用户权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "token", value = "token凭证", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = "account/login", method = RequestMethod.POST)
    public String login(@RequestParam Integer id, @RequestParam String token, HttpSession session){
        log.info("call login, parameter:{}", id,token);
        String[] permission = null;
        try{
            //获取用户信息
            User user = userService.queryUsers(id);
            //获取用户角色权限
            RoleResult roleResult = permissionService.queryPermission(user.getId());
            //返回角色权限数组
            permission = roleResult.getBusinesspermissionstring().split(",");
            //将权限存入Session
            session.setAttribute("userToken",token);
            session.setAttribute("roleResult",roleResult);
            log.info("success to login, RESULT:{}", permission);
        }catch (JsureException jex){
            log.info("failed to login, RESULT:{}", permission);
        }catch (Exception e){
            log.error("failed to login,parameter:{}, cause:{}", permission,Throwables.getStackTraceAsString(e));
        }
        return JsonUtils.obj2json(permission);
    }
}
