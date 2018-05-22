package com.jsure.datacenter.model.enummodel;

import com.google.common.base.Objects;
import lombok.Getter;

/**
 * @Author: wuxiaobiao
 * @Description: 异常枚举
 * @Date: Created in 2018/4/9
 * @Time: 14:00
 * I am a Code Man -_-!
 */
public enum SystemErrorEnum {
    /**
     * token
     */
    JWT_ERRCODE_NULL("4000", "token不存在"),
    JWT_ERRCODE_EXPIRE("4001", "token过期"),
    JWT_ERRCODE_FAIL("4002", "token验证不通过"),

    /**
     *
     */
    ERROR_CODE_341000("200", "处理成功"),
    ERROR_CODE_341FFF("341FFF", "程序异常"),
    ERROR_CODE_341001("341001", "亲，您查询的信息不存在哦"),
    ERROR_CODE_341002("341002", "参数不能为空"),
    ERROR_CODE_341003("341003", "该用户没有绑定角色"),
    ERROR_CODE_341004("341004", "未查询到用户信息"),
    ERROR_CODE_341005("341005", "用户名不存在"),
    ERROR_CODE_341006("341006", "密码错误");


    @Getter
    private String errorCode;
    @Getter
    private String errorDesc;


    SystemErrorEnum(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public static String explain(String errorCode) {
        for (SystemErrorEnum systemErrorEnum : SystemErrorEnum.values()) {
            if (Objects.equal(errorCode, systemErrorEnum.errorCode)) {
                return systemErrorEnum.errorDesc;
            }
        }
        return errorCode;
    }
}
