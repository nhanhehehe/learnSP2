package hoc.tot.nhan.controller;

import com.nimbusds.jose.JOSEException;
import hoc.tot.nhan.dto.request.ApiResponse;
import hoc.tot.nhan.dto.request.AuthenticationRequest;
import hoc.tot.nhan.dto.request.IntrospectRequest;
import hoc.tot.nhan.dto.response.AuthenticationResponse;
import hoc.tot.nhan.dto.response.IntrospectResponse;
import hoc.tot.nhan.service.AuthenticationService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var validResult = authenticationService.authenthicate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(validResult)
                .build();
    };

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticationService.verifyToken(request))
                .build();
    }
}
