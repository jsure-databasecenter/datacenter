package com.jsure.datacenter.enummodel;

import com.google.common.base.Objects;

/**
 * @Author: wuxiaobiao
 * @Description: 异常枚举
 * @Date: Created in 2018/4/9
 * @Time: 14:00
 * I am a Code Man -_-!
 */
public enum  JsureErrorEnum {

    ERROR_CODE_341FFF("341FFF", "程序异常"),
    ERROR_CODE_341001("341001", "亲，您查询的信息不存在哦"),
    ERROR_CODE_341002("341002", "参数不能为空");


    private String errorCode;
    private String errorDesc;


    JsureErrorEnum(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public static String explain(String errorCode) {
        for (JsureErrorEnum jsureErrorEnum : JsureErrorEnum.values()) {
            if (Objects.equal(errorCode, jsureErrorEnum.errorCode)) {
                return jsureErrorEnum.errorDesc;
            }
        }
        return errorCode;
    }
}
