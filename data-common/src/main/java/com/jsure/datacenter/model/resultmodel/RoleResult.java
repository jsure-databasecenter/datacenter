package com.jsure.datacenter.model.resultmodel;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/10
 * @Time: 17:51
 * I am a Code Man -_-!
 */
@Setter
@Getter
public class RoleResult implements Serializable {

    private static final long serialVersionUID = -97513035177369723L;

    private Integer id;

    private String name;

    private Integer parentid;

    private String info;

    private String businesspermissionstring;

    private Long createtime;

    private Long updatetime;

    private Boolean isdelete;

    private Integer status;

    private Integer writerid;

    private Integer writerroleid;

    private Long departmentid;

}
