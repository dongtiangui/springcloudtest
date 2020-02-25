package com.integral.boot.domain.system;

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

    public CustomErrorType(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
