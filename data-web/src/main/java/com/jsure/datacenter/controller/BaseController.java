package com.jsure.datacenter.controller;

import com.google.common.collect.Maps;
import com.jsure.datacenter.constant.CmsConstantInsensitive;
import com.jsure.datacenter.model.enummodel.SystemErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wuxiaobiao
 * @Description: 基类
 * @Date: Created in 2018/5/21
 * @Time: 16:02
 * I am a Code Man -_-!
 */
@Controller
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession session;

    /**
     * <功能详细描述> 参数失败返回信息
     *
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> paramFailedMessage() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", "1");
        map.put("msg", "参数异常");
        map.put("data", null);
        return map;
    }

    /**
     * <功能详细描述> 成功返回数据
     *
     * @param data
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> successData(String msg, Map<String, Object> data) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("resCode", SystemErrorEnum.ERROR_CODE_341000.getErrorCode());
        map.put("resMsg", msg);
        map.put("data", data);
        return map;
    }

    /**
     * <功能详细描述> 返回失败数据
     *
     * @param data
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Map<String, Object> failedData(String code, String msg, Map<String, Object> data) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("resCode", code);
        map.put("resMsg", msg);
        map.put("data", data);
        return map;
    }

    /**
     * 将request的参数转换成字符串
     *
     * @param request
     * @return
     */
    protected String requestParamToString(HttpServletRequest request) {
        Map<String, String> paramMap = toStringParam(request);
        Map<String, String> pathVarMap = toStringPathVar(request);
        StringBuffer resultSB = new StringBuffer();
        //拼接param字符串
        if (paramMap != null) {
            resultSB.append("REQUEST.PARAM").append(paramMap);
        }
        //拼接pathVariable字符串
        if (pathVarMap != null) {
            resultSB.append("REQUEST.PATH_VARIABLE").append(pathVarMap);
        }
        return resultSB.toString();
    }

    /**
     * 获取request的param参数对应的map
     *
     * @param request
     * @return
     */
    private Map toStringParam(HttpServletRequest request) {
        Map<String, String> paramMap = null;
        for (Object key : request.getParameterMap().keySet()) {
            if (key == null) {
                continue;
            }
            if (paramMap == null) {
                paramMap = new HashMap<>();
            }
            String tempVal = request.getParameter(key.toString()) != null ? request.getParameter(key.toString()).toString() : null;
            //保密属性脱敏
            if (CmsConstantInsensitive.INSENSITIVE_LIST.contains(key.toString().toLowerCase())) {
                tempVal = CmsConstantInsensitive.INSENSITIVE_STRING;
            }
            paramMap.put(key.toString(), tempVal);
        }
        return paramMap;
    }

    /**
     * 获取pathVariable注解的参数map
     *
     * @param request
     * @return
     */
    private Map toStringPathVar(HttpServletRequest request) {
        Map pathVariableMap = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) != null ? (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) : null;
        if (pathVariableMap.isEmpty()) {
            pathVariableMap = null;
        }
        return pathVariableMap;
    }
}
