package com.jsure.datacenter.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: LeonWong
 * Date: 2016-03-26
 * Time: 19:08
 * To change this template use File | Settings | Editor | File and Code Templates.
 */
public class CmsConstantInsensitive {

    /**
     * 脱敏替换字符
     */
    public static final String INSENSITIVE_STRING = "******";

    /**
     * 敏感属性列表
     */
    public static List<String> INSENSITIVE_LIST = new ArrayList<>();

    static {
        INSENSITIVE_LIST.add("passwd");
        INSENSITIVE_LIST.add("password");
        INSENSITIVE_LIST.add("initPasswd");
    }
}
