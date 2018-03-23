package org.paul.lib.base;

import java.io.Serializable;

public class BaseBean implements Serializable{

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    protected int code;
    protected String message;

}
