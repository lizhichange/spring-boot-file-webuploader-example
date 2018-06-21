package com.webuploader.enums;

import lombok.Getter;

/**
 * @author Administrator
 */

@Getter

public enum LatestUrlEnum implements BaseEnum {
    /**
     * http://caipiao.163.com/award/
     */
    WY("WY", "WY", "http://caipiao.163.com/award/", "awardList"),

    /**
     * http://baidu.lecai.com/lottery/draw/
     */

    BD("BD", "BD", "http://baidu.lecai.com/lottery/draw/", "kj_tab"),;

    private final String code;
    private final String desc;
    private final String url;
    private final String className;




    LatestUrlEnum(String code, String desc, String url, String className) {
        this.code = code;
        this.desc = desc;
        this.url = url;
        this.className = className;
    }

}
