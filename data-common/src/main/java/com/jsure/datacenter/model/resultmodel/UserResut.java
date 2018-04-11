package com.jsure.datacenter.model.resultmodel;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: wuxiaobiao
 * @Description:
 * @Date: Created in 2018/4/9
 * @Time: 18:22
 * I am a Code Man -_-!
 */
@Getter
@Setter
public class UserResut implements Serializable{

    private static final long serialVersionUID = -4624117358333049614L;

    private Integer id;

    private Integer roleid;

    private String loginname;

    private Long deleteid1;

    private String fullname;

    private Long mobile;

    private Long deleteid2;

    private String telephone;

    private Integer provinceid;

    private Integer cityid;

    private Integer districtid;

    private String address;

    private String weixin;

    private Long qq;

    private Short job;

    private Long createtime;

    private Long updatetime;

    private Boolean isdelete;

    private Integer status;

    private String password;

    private Byte sex;

    private Integer writerid;

    private Integer writerroleid;

    private Integer iconid;

    private String dduserid;
}
