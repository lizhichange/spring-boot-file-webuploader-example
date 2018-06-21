package com.webuploader.enums;

import lombok.Getter;

/**
 * @author Administrator
 */

@Getter
public enum BeautyNewsTypeEnum implements BaseEnum {

    /**
     * 最新
     */
    NEWEST("NEWEST", "最新"),
    /**
     * 明星
     */
    STAR("STAR", "明星"),
    /**
     * 护肤
     */
    SKIN("SKIN", "护肤"),
    /**
     * 造型
     */
    MAKEUP("MAKEUP", "造型"),;

    private String code;

    private String desc;


    BeautyNewsTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
