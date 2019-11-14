package com.ecommodity.bootstrap.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class ResponseErrorHandlerCommodity extends DefaultResponseErrorHandler {
    @Override
    protected boolean hasError(HttpStatus statusCode) {
        return super.hasError(statusCode);
    }
}
