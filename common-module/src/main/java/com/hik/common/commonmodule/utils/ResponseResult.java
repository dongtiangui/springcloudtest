package com.hik.common.commonmodule.utils;

import java.io.Serializable;

/**
 * api返回封装实例
 * @param <T>
 */
public class ResponseResult <T> implements Serializable {


    private static final long serialVersionUID = -5372450875750675775L;

    /**
     * 编码
     */
    private Integer code;


    /**
     * 消息
     */
    private String msg;


    /**
     * 数据
     */
    private T data;


    public ResponseResult() {
    }


    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public static ResponseResult success() {
        ResponseResult result = new ResponseResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }


    public static ResponseResult success(Object data) {
        ResponseResult result = new ResponseResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }


    public static ResponseResult failure(ResultCode resultCode) {
        ResponseResult result = new ResponseResult();
        result.setResultCode(resultCode);
        return result;
    }


    public static ResponseResult failure(ResultCode resultCode, Object data) {
        ResponseResult result = new ResponseResult();
        result.setResultCode(resultCode);
        return result;
    }


    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
