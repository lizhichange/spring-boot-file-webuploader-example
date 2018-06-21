package com.webuploader.enums;


import lombok.Getter;

@Getter
public enum SourceEnum implements BaseEnum {
    /**
     * 网易
     */
    WY("WY", "网易"),
    /**
     * 新浪
     */
    SINA("SINA", "新浪"),;
    private final String code;
    private final String desc;

    SourceEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }



}
