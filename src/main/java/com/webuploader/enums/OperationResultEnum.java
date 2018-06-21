package com.webuploader.enums;


import lombok.Getter;

@Getter
public enum OperationResultEnum implements BaseEnum {
    /**
     * 成功。
     */
    OPERATION_SUCCESS("SUCCESS", "操作成功"),

    /**
     * 失败。
     */
    OPERATION_FAILURE("FAILURE", "操作失败"),

    /**
     * 异常。
     */
    OPERATION_EXCEPTION("EXCEPTION", "操作异常"),

    /**
     * 参数非法
     */
    ARGUMENTS_ILLEGAL("ARGUMENTS_ILLEGAL", "参数非法"),

    /**
     * 支付订单已经成功
     */
    PAY_ORDER_STATUS_IS_SUCCESS("ORDER_STATUS_IS_SUCCESS", "支付订单已经成功"),;

    /**
     * code码。
     */
    private String code;
    /**
     * 描述 。
     */
    private String desc;

    /**
     * 私有构造方法。
     *
     * @param code code码。
     * @param desc 描述。
     */
    OperationResultEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;

    }

}
