package com.jsure.datacenter.model.entitymodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role {
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