package com.alipay.demo.pojo;

public class SuccessResponse {
    private Boolean Success;

    public SuccessResponse(){}

    public SuccessResponse(Boolean success) {
        Success = success;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }
}
