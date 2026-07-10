package hoc.tot.nhan.exception;

import java.lang.classfile.constantpool.ConstantDynamicEntry;

public enum ErrorCode {
    UNCATEGORIZED_CODE(69, "uncategorized exception" ),
    INVALIDKEY(99, "uncategorized exception" ),
    USER_EXISTED(1001, "user existed"),
    USERNAME_INVALID(1003, "username must be at least 3 characters"),
    PASSWORD_INVALID(1002, "password must be at least 8 characters"),
    EMAIL_INVALID(1004, "email is not valid"),
    USERNAME_NOT_EXISTED(1005, "Username is not existed"),
    UNAUTHENTICATED_PASSWORD(1006, "unauthenticated"),
    USER_NOT_EXISTED(1007, "user does not exist"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

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

    private int code;
    private String message;
}
