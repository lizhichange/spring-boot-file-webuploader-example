package com.webuploader.enums;

import lombok.Getter;

/**
 * @author Administrator
 */

@Getter
public enum LotteryBDTypeEnum implements BaseEnum {
    /**
     * 双色球
     */
    SSQ("SSQ", "双色球", "50", "historylist", "draw_list", " 21:30:00"),
    /**
     * 福彩3D
     */


    FC3D("FC3D", "福彩3D", "52", "draw_list", "draw_list", " 20:30:00"),
    /**
     * 新3D
     */
    X3D("X3D", "新3D", "53", "draw_list", "draw_list", " 20:30:00"),

    /**
     * 七乐彩
     */
    QLC("QLC", "七乐彩", "51", "draw_list", "draw_list", " 21:30:00"),

    /**
     * 大乐透
     */
    DLT("DLT", "大乐透", "1", "draw_list", "draw_list", " 20:30:00"),

    /**
     * 七星彩
     */
    QXC("QXC", "七星彩", "2", "draw_list", "draw_list", " 20:30:00"),

    /**
     * 排列3
     */
    PL3("PL3", "排列3", "3", "draw_list", "draw_list", " 20:30:00"),

    /**
     * 排列5
     */
    PL5("PL5", "排列5", "4", "draw_list", "draw_list",
            " 20:30:00"), SFC("SFC", "胜负彩", "7", "draw_list", "draw_list", ""),;

    private final String code;
    private final String desc;
    private final String url;
    private final String id;
    private final String className;
    private final String gmtDrawLottery;


    public String getUrl() {
        return "http://baidu.lecai.com/lottery/draw/list/" + url + "?d";
    }


    LotteryBDTypeEnum(String code, String desc, String url, String id, String className,
                      String gmtDrawLottery) {
        this.code = code;
        this.desc = desc;
        this.url = url;
        this.id = id;
        this.className = className;
        this.gmtDrawLottery = gmtDrawLottery;
    }


}
