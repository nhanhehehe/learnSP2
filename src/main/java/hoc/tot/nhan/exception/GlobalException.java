package hoc.tot.nhan.exception;

import hoc.tot.nhan.dto.request.ApiResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class GlobalException {

    private static final String ValidationValue = "min";

    // exception chua biet cho cai gi
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException e) {
        log.error("Exception: ", e);
        ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());
        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(apiResponse);
    }

    // check class exception va phai comment method dau tien moi chay duoc
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handle(Exception ex){
//
//        System.out.println(ex.getClass());
//
//        return ResponseEntity.badRequest().build();
//    }
    // exception cua annotation valid(@Size, @Email, ...)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException e) {
        String enumkey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALIDKEY;

        Map<String, Object> attributes = null;

        try {
            errorCode = ErrorCode.valueOf(enumkey);

            var constraintViolation = e.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();
            log.info("attribute of constraint {}",attributes);

        } catch (IllegalArgumentException exception) {

        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(Objects.nonNull(attributes) ?
                bindAttribute(errorCode.getMessage(), attributes) :
                errorCode.getMessage()
                );
        apiResponse.setCode(errorCode.getCode());
        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(apiResponse);
    }

    //binding gia tri tu attribute cua annotation validation vao trong message error (validation nang cao)
    String bindAttribute (String message , Map<String, Object> attributes) {
        var attributeValue = String.valueOf(attributes.get(ValidationValue));

        var result = message.replace("{" + ValidationValue + "}", attributeValue );

        return result;
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDenied(AccessDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());
        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(apiResponse);
    }
}
