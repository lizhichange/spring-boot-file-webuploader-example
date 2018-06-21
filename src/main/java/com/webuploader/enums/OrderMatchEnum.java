package com.webuploader.enums;

import lombok.Getter;

@Getter
public enum OrderMatchEnum implements BaseEnum {
    /**
     * 网易
     */
    WY("WY", "网易", "http://caipiao.163.com/order/preBet_jczqspfmixp.html", "unAttention"),;

    private final String code;
    private final String desc;
    private final String url;
    private final String className;


    OrderMatchEnum(String code, String desc, String url, String className) {
        this.code = code;
        this.desc = desc;
        this.url = url;
        this.className = className;
    }

}
