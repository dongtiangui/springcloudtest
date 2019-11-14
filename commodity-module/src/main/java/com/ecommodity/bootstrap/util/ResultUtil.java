package com.ecommodity.bootstrap.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ResultUtil<T> implements Serializable {

    private static final long serialVersionUID = -9115904851491358277L;
    private Integer code;

    private String msg;

    private T data;

    private boolean login;

    public ResultUtil(){}

    public ResultUtil(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultUtil(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultUtil<Object> success(Object data) {
        ResultUtil<Object> resultUtil = new ResultUtil<>();
        resultUtil.setCode(0);
        resultUtil.setData(data);
        return resultUtil;
    }

    /**
     * 成功
     *
     * @return
     */
    public static ResultUtil success() {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(0);
        return resultUtil;
    }

    /**
     * 错误返回
     *
     * @param resultEnum
     * @return
     */
    public static ResultUtil error(ResultEnum resultEnum) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(resultEnum.getCode());
        resultUtil.setMsg(resultEnum.getMsg());
        return resultUtil;
    }

    /**
     * 返回失败
     *
     * @param msg
     * @return
     */
    public static ResultUtil error(String msg) {
        ResultUtil resultUtil = new ResultUtil();
        resultUtil.setCode(-1);
        resultUtil.setMsg(msg);
        return resultUtil;
    }
}
