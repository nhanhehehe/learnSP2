package hoc.tot.nhan.service;

import hoc.tot.nhan.Role;
import hoc.tot.nhan.dto.request.UserCreationRequest;
import hoc.tot.nhan.dto.request.UserUpdateRequest;
import hoc.tot.nhan.dto.response.UserResponse;
import hoc.tot.nhan.entity.User;
import hoc.tot.nhan.exception.AppException;
import hoc.tot.nhan.exception.ErrorCode;
import hoc.tot.nhan.mapper.UserMapper;
import hoc.tot.nhan.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    UserRepository userRepository;
    UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<String> roles = new HashSet<String>();
        roles.add(Role.USER.name());

        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }


    public UserResponse updateUser(String id, UserUpdateRequest request) {
        // user request -> update(User user) -> UserResponse response

        // create user
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_EXISTED));

        // update user request to user
        userMapper.updateUser(user, request);

        // return user to user response
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUserById(String id) {
        User user =  userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userRepository.delete(user);
    }

    public UserResponse getUserById(String id) {
        User use = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(use);
    }

    public List<UserResponse> getAllUsers() {
    // áp dụng stream và map
        return userRepository.findAll().stream()
                .map(user -> userMapper.toUserResponse(user)).toList();
        }

    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }
}
