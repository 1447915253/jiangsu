package com.rmkj.microcap.common.bean;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/**
 * Created by zhangbowen on 16-6-29.
 */
public class ResponseErrorEntity extends ResponseEntity {
    private StatusCode statusCode;
    public ResponseErrorEntity(HttpStatus statusCode) {
        super(statusCode);
    }

    public ResponseErrorEntity(StatusCode body, HttpStatus statusCode) {
        super(new ResultError(body.getValue(),body.getDescription()), statusCode);
        this.statusCode = body;
    }
    public ResponseErrorEntity(ResultError error, HttpStatus statusCode) {
        super(error, statusCode);
    }

    public ResponseErrorEntity(String errorMsg, HttpStatus statusCode) {
        super(new ResultError(errorMsg), statusCode);
    }

    public ResponseErrorEntity(MultiValueMap headers, HttpStatus statusCode) {
        super(headers, statusCode);
    }

    public ResponseErrorEntity(Object body, MultiValueMap headers, HttpStatus statusCode) {
        super(body, headers, statusCode);
    }
    public StatusCode getCode(){
        return statusCode;
    }
}
