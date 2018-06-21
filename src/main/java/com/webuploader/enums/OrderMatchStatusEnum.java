package com.webuploader.enums;


import lombok.Getter;

/**
 * @author Administrator
 */

@Getter
public enum OrderMatchStatusEnum implements BaseEnum {
    /**
     * Y
     */
    Y("Y", ""),
    /**
     * N
     */

    N("N", ""),;

    private final String code;
    private final String desc;



    OrderMatchStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
