package com.deepblueai.gateway.filters;

public class Result {
    private Boolean flag;
    private String message;

    public Result(Boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public Result() {
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
