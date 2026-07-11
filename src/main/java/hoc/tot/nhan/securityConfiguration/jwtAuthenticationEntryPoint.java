package hoc.tot.nhan.securityConfiguration;

import hoc.tot.nhan.dto.request.ApiResponse;
import hoc.tot.nhan.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

public class jwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        response.setContentType("application/json");
        response.setStatus(errorCode.getHttpStatusCode().value());

        ApiResponse<?> apiReponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        ObjectMapper object = new ObjectMapper();
        response.getWriter().write(object.writeValueAsString(apiReponse));
        response.flushBuffer();
    }
}
