package com.jsure.datacenter.utils;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/9
 * @Time: 13:14
 * I am a Code Man -_-!
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -8705057226888494469L;
    private boolean success;
    private T result;
    private String errorCode;
    private String errorMsg;

    public Response() {
    }

    public Response(T result) {
        this.success = true;
        this.result = result;
    }

    public Response(boolean flag, T result) {
        if(flag) {
            this.success = true;
            this.result = result;
        } else {
            this.success = false;
            this.errorCode = (String)result;
        }

    }

    public Response(String errorCode) {
        this.success = false;
        this.errorCode = errorCode;
    }

    public Response(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.success = false;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            Response response = (Response)o;
            return this.success != response.success?false:(!this.errorCode.equals(response.errorCode)?false:this.result.equals(response.result));
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result1 = this.success?1:0;
        result1 = 31 * result1 + this.result.hashCode();
        result1 = 31 * result1 + this.errorCode.hashCode();
        return result1;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("success", this.success).add("result", this.result).add("errorCode", this.errorCode).add("errorMsg", this.errorMsg).omitNullValues().toString();
    }

}
