package org.zhangyinhao.base.result;

public enum BaseCode {
    SUCCESS(0, "成功"),
    ERROR(-1, "错误");

    private final int code;
    private final String message;

    private BaseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
