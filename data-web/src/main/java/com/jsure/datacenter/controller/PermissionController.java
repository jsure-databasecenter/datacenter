package com.jsure.datacenter.controller;

import com.jsure.datacenter.exception.JsureException;
import com.jsure.datacenter.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("api/permission/")
@Api(description = "权限接口")
public class PermissionController {

    @RequestMapping(value = "userpermission",method = RequestMethod.POST)
    @ApiOperation(value="获取用户权限列表", notes="获取用户权限")
    public String userPermission(HttpSession session){
        log.info("request userPermission");
        String permission = null;
        try{
            permission = (String)session.getAttribute("permission");
        }catch (JsureException jex){

        }catch (Exception e){

        }
        return JsonUtils.obj2json(permission);

    }
}
