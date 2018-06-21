package com.webuploader.enums;


import lombok.Getter;

@Getter
public enum LotteryWYTypeEnum implements BaseEnum {
    /**************************
     * 数字彩票
     * *****************/
    SSQ("SSQ", "双色球", "", "", ""),

    DLT("DLT", "大乐透", "http://caipiao.163.com/award/dlt/", ".html#from=kjdt", "search_zj_left"),

    SD("SD", "3D", "", "", ""),

    PL3("PL3", "排列3", "", "", ""),

    PL5("PL5", "排列5", "", "", ""),

    QXC("QXC", "七星彩", "", "", ""),

    QLC("QLC", "七乐彩", "", "", ""),

    /**************************** 竞技体育 *****************/

    SFC("SFC", "胜负彩", "http://caipiao.163.com/award/sfc/", ".html#from=kjdt", "sfcAgainst"),

    RX9("RX9", "任选九", "", "", ""),

    lCBQC("lCBQC", "六场半全场", "", "", ""),

    SCJQ("SCJQ", "四场进球", "", "", ""),

    ZQDC("ZQDC", "足球单场", "http://caipiao.163.com/award/dcspf/?period=", "#from=kjdt", "zqdc_table"),

    SFGG("SFGG", "胜负过关", "http://caipiao.163.com/award/sfgg/?period=", "#from=kjdt", "zqdc_table"),

    JCZQ("JCZQ", "竞彩足球", "http://caipiao.163.com/award/jczqspfp.html?selectedDate=", "",
            "mt_10"),
    JCLQ("JCLQ", "竞彩篮球", "", "", ""),

    /**************************** 高频彩 *****************/

    SYX5("SYX5", "11选5", "", "", ""),

    LSYX5("LSYX5", "老11选5", "", "", ""),

    GDSYX5("GDSYX5", "广东11选5", "", "", ""),

    HYSYX5("HYSYX5", "好运11选5", "", "", ""),

    CQSYX5("CQSYX5", "重庆11选5", "", "", ""),

    YLSYX5("YLSYX5", "易乐11选5", "", "", ""),

    LNSYX5("LNSYX5", "辽宁11选5", "", "", ""),

    K3("K3", "快3", "", "", ""),

    JSK3("JSK3", "江苏快3", "", "", ""),

    XK3("XK3", "新快3", "", "", ""),

    KLPK("KLPK", "快乐扑克", "", "", ""),

    HBK3("HBK3", "湖北快3", "", "", ""),

    LSSC("LSSC", "老时时彩", "", "", ""),

    XSSC("XSSC", "新时时彩", "", "", ""),

    KL8("KL8", "快乐8", "", "", ""),

    GDKLSF("GDKLSF", "广东快乐十分", "", "", ""),;

    private String code;
    private String desc;
    private String url;
    private String suffix;
    private String className;

    LotteryWYTypeEnum(String code, String desc, String url, String suffix, String className) {
        this.code = code;
        this.desc = desc;
        this.url = url;
        this.suffix = suffix;
        this.className = className;
    }



}
