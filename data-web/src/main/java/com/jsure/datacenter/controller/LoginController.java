package com.jsure.datacenter.controller;

import com.google.gson.Gson;
import com.jsure.datacenter.entitymodel.User;
import com.jsure.datacenter.exception.JsureException;
import com.jsure.datacenter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/8
 * @Time: 15:04
 * I am a Code Man -_-!
 */
@RestController
@RequestMapping(value = "api/")
@Api(description = "登录注册")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value="登录", notes="根据token和用户id来获取用户权限")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestBody User user){
        logger.info("==");
        Gson json = new Gson();
        User users = null;
        try{
            users = user;
//            users = userService.queryUsers(1);
        }catch (JsureException jex){
            logger.info("a");
        }catch (Exception e){
            logger.info("b");
        }
        return json.toJson(users);
    }
}
