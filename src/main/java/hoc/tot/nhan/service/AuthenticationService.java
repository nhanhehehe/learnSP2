package hoc.tot.nhan.service;

import hoc.tot.nhan.dto.request.AuthenticationRequest;
import hoc.tot.nhan.dto.response.AuthenticationResponse;
import hoc.tot.nhan.entity.User;
import hoc.tot.nhan.exception.AppException;
import hoc.tot.nhan.exception.ErrorCode;
import hoc.tot.nhan.repository.UserRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    public Boolean authenthicate (AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
