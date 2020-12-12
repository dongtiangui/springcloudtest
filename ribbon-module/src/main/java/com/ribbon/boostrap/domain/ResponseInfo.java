package com.ribbon.boostrap.domain;

import com.alibaba.fastjson.JSON;

public class ResponseInfo<T> {
    //0表示正常
    public static final int SUCCESS_CODE = 0;

    public static final int ERROR_CODE = 1;

    /**
     * 成功
     */
    public static final ResponseInfo SUCCESS = new ResponseInfo(SUCCESS_CODE, "SUCCESS");
    /**
     * 失败
     */
    public static final ResponseInfo ERROR = new ResponseInfo(ERROR_CODE, "ERROR");

    //返回状态码
    private int code;

    //提示信息
    private String msg;

    //数据类型
    private T data;

    public ResponseInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseInfo() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
    public String toString() {
        return JSON.toJSONString(this);
    }

}
