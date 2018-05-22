package com.jsure.datacenter.model.entitymodel;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TUser {
    private Integer id;

    private String userName;

    private String password;

    private Date createDate;

}