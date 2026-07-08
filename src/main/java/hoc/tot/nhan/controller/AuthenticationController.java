package hoc.tot.nhan.controller;

import hoc.tot.nhan.dto.request.ApiResponse;
import hoc.tot.nhan.dto.request.AuthenticationRequest;
import hoc.tot.nhan.dto.response.AuthenticationResponse;
import hoc.tot.nhan.service.AuthenticationService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        Boolean validResult = authenticationService.authenthicate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .authenticated(validResult)
                        .build())
                .build();
    };
}
