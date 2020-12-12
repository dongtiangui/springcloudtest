package com.hik.common.commonmodule.utils;

/**
 *  返回code 枚举工具类
 */
public enum ResultCode {

    SUCCESS(1, "成功"),


    FAILURE(0, "失败");

    /**
     * 编码
     */
    private Integer code;

    /**
     * 消息
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public Integer code() {
        return this.code;
    }


    public String message() {
        return this.message;
    }


    public static Integer getCode(String name) {
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.name().equals(name)) {
                return resultCode.code;
            }
        }
        return null;
    }


    public static String getMessage(String name) {
        for (ResultCode resultCode : ResultCode.values()) {
            if (resultCode.name().equals(name)) {
                return resultCode.message;
            }
        }
        return name;
    }
    @Override
    public String toString() {
        return this.name();
    }
}
