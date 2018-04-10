package com.jsure.datacenter.controller;

import com.google.gson.Gson;
import com.jsure.datacenter.exception.JsureException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/10
 * @Time: 11:39
 * I am a Code Man -_-!
 */
@RestController
@RequestMapping("api/permission/")
@Api(description = "权限接口")
public class PermissionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "userpermission",method = RequestMethod.POST)
    @ApiOperation(value="获取用户权限列表", notes="获取用户权限")
    public String userPermission(HttpSession session){
        logger.info("request userPermission！param:{},url:{}");
        Gson gson = new Gson();
//        Map<String, Object> map = Maps.newHashMap();
        String permission = null;
        try{
            permission = (String)session.getAttribute("permission");
        }catch (JsureException jex){

        }catch (Exception e){

        }
        return gson.toJson(permission);

    }
}
