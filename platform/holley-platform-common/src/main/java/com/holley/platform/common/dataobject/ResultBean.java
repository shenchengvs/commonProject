package com.holley.platform.common.dataobject;

import java.io.Serializable;

/**
 * 通用数据调用结果返回bean
 * 
 * @author zhouli
 */
public class ResultBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean           success          = true;
    private String            message;
    private int               errorCode        = 0;
    private Object            data;

    public ResultBean() {

    }

    public ResultBean(boolean success, int errorCode) {
        this.success = success;
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        this.success = false;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
