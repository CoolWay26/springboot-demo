package com.coolway.common;

import java.io.Serializable;

public class ResponseResult implements Serializable {
    private boolean resultFlag = true;
    private String errorCode;
    private String message;
    private Object data;
    public ResponseResult() {
    }

    public ResponseResult(boolean resultFlag, String message) {
        this.resultFlag = resultFlag;
        this.message = message;
    }

    public ResponseResult(boolean resultFlag, Object data) {
        this.resultFlag = resultFlag;
        this.data = data;
    }

    public ResponseResult(boolean resultFlag, String message, Object data) {
        this.resultFlag = resultFlag;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(boolean resultFlag, String errorCode, String message) {
        this.resultFlag = resultFlag;
        this.errorCode = errorCode;
        this.message = message;
    }

    public ResponseResult(boolean resultFlag, String errorCode, String message, Object data) {
        this.resultFlag = resultFlag;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public ResponseResult resultFlag(boolean resultFlag) {
        this.resultFlag = resultFlag;
        return this;
    }

    public ResponseResult errorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public ResponseResult message(String message) {
        this.message = message;
        return this;
    }

    public ResponseResult data(Object data) {
        this.data = data;
        return this;
    }

    public boolean isResultFlag() {
        return this.resultFlag;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getData() {
        return this.data;
    }
}
