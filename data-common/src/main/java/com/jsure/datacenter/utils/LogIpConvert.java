package com.jsure.datacenter.utils;


import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: wuxiaobiao
 * @Description: 自定义日志格式获取ip
 * @Date: Created in 2018/4/12
 * @Time: 13:47
 * I am a Code Man -_-!
 */
public class LogIpConvert extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        String addr = null;
        try {
            //获得本机IP
            addr = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            addr = "127.0.0.1";
        }
        return addr;
    }
}
