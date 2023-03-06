package com.softeem.crm.exceptions;

/**
 * 无权访问异常
 */
public class NoAuthException extends RuntimeException {
    private Integer code = 300;
    private String msg = "无权访问!";

    public NoAuthException() {
    }

    public NoAuthException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public NoAuthException(Integer code) {
        super("无权访问!");
        this.code = code;
    }

    public NoAuthException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static void main(String[] args) {
        NoAuthException authException = new NoAuthException();
        System.out.println("authException.getCode() = " + authException.getCode());
    }
}
