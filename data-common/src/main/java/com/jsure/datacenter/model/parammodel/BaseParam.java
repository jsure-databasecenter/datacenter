package com.jsure.datacenter.model.parammodel;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wuxiaobiao
 * @Description: Base入参
 * @Date: Created in 2018/4/11
 * @Time: 14:56
 * I am a Code Man -_-!
 */
@Getter
@Setter
public class BaseParam implements Serializable {

    private static final long serialVersionUID = 7918016316921875617L;

    /**
     * 是否抛异常
     */
    protected Boolean isNullError = true;

    /**
     * 分页当前页数（从1开始）
     */
    protected Integer pageCurrent;
    /**
     * 分页每页个数
     */
    protected Integer pageSize;

    /**
     * 时间范围查询参数--开始时间
     */
    protected Date beginTime;

    /**
     * 时间范围查询参数--结束时间
     */
    protected Date endTime;
    /**
     * 返回只和
     */
    protected String count;

    /**
     * 排序字段
     */
    protected String orderField;

    /**
     * 排序規則
     */
    protected String orderRule;

    /**
     * 返回字段时间 格式yyyy-MM-dd HH24:mm:ss
     */
    protected String stringTime;

    /**
     * 是否下载
     */
    protected Boolean isDownload = false;
}
