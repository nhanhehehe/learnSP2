package hoc.tot.nhan.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import hoc.tot.nhan.dto.request.AuthenticationRequest;
import hoc.tot.nhan.dto.request.IntrospectRequest;
import hoc.tot.nhan.dto.response.AuthenticationResponse;
import hoc.tot.nhan.dto.response.IntrospectResponse;
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

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    protected final static String secretKey = "368ab53ca186843c70301a5c3f75a0f974443d11f6e3d21163268adcdcb7e25a";

    public AuthenticationResponse authenthicate (AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var authenticated =  passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED_PASSWORD);
        }
        var token = generateToken(user.getUsername());
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }

    public IntrospectResponse verifyToken (IntrospectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();

        JWSVerifier verifier = new MACVerifier(secretKey.getBytes());

        SignedJWT jwt = SignedJWT.parse(token);
        Boolean expiryTime = jwt.getJWTClaimsSet().getExpirationTime().after(new Date());
        Boolean verified = jwt.verify(verifier);

        return IntrospectResponse.builder()
                .Valid(verified && expiryTime)
                .build();
    }

    public String generateToken(String username) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                // ten domain; ai la nguoi da issue token nay
                .issuer("hoc.tot")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                // custome claim
                .claim("userId", "custome")
                .build();
        // payload gom cac claim ma claim la cac data trong body
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }


    }
}
