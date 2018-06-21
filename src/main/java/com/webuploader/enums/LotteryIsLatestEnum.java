package com.webuploader.enums;


import lombok.Getter;

/**
 * @author Administrator
 */

@Getter
public enum LotteryIsLatestEnum implements BaseEnum {
    /**
     * 是
     */
    Y("Y", "是"),
    /**
     * 否
     */
    N("N", "否"),;


    private final String code;
    private final String desc;

    LotteryIsLatestEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
