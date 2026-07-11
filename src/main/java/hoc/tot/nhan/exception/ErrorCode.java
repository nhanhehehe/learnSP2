package hoc.tot.nhan.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_CODE(69, "uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALIDKEY(99, "uncategorized exception", HttpStatus.BAD_REQUEST ),
    USER_EXISTED(1001, "user existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "username must be at least 3 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1002, "password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(1004, "email is not valid", HttpStatus.BAD_REQUEST),
    USERNAME_NOT_EXISTED(1005, "Username is not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "unauthenticated", HttpStatus.UNAUTHORIZED),
    USER_NOT_EXISTED(1007, "user does not exist",  HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1008, "unauthorized",  HttpStatus.FORBIDDEN),
    ;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode ) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    private int code;
    private HttpStatusCode httpStatusCode;
    private String message;
}
