package com.oauth2.oauthsecurity.util;

import lombok.Getter;

@Getter
public enum  ResultEnum {

    ACCESS_NOT(501, "权限不足"),

    TOKEN_IS_NOT_VALID(502, "token无效，请重新登录");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
