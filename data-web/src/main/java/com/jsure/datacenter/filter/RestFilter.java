package com.jsure.datacenter.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wuxiaobiao
 * @Description: 前后端分离RESTful接口过滤器
 * @Date: Created in 2018/5/22
 * @Time: 13:58
 * I am a Code Man -_-!
 */
public class RestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = null;
        if (request instanceof HttpServletRequest) {
            req = (HttpServletRequest) request;
        }
        HttpServletResponse res = null;
        if (response instanceof HttpServletResponse) {
            res = (HttpServletResponse) response;
        }
        if (null != req && null != res) {
            //设置允许跨域的配置,这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
            res.setHeader("Access-Control-Allow-Origin", "*");
            // 允许的访问方法
            res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
            // Access-Control-Max-Age 用于 CORS 相关配置的缓存
            res.setHeader("Access-Control-Max-Age", "3600");
            res.setHeader("Access-Control-Allow-Headers", "Authorization, token, Origin, X-Requested-With, Content-Type, Accept");
        }
        String method = req.getMethod();
        if ("OPTIONS".equals(method)) {
            res.setStatus(HttpServletResponse.SC_OK);
        }else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}