package com.oauth2.oauthsecurity.domain;

import com.google.common.base.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomErrorType that = (CustomErrorType) o;
        return status == that.status &&
                Objects.equal(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status, message);
    }
}
