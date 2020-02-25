package com.oauth2.oauthsecurity.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
@Getter
@Setter
@ToString
public class CustomErrorType implements Serializable {

    private static final long serialVersionUID = 8936872291119779123L;
    private int status;
    private String message;

//    无参构造函数
    public CustomErrorType(){}

    public CustomErrorType(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
