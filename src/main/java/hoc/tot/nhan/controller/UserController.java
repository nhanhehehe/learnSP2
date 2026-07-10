package hoc.tot.nhan.controller;

import hoc.tot.nhan.dto.request.ApiResponse;
import hoc.tot.nhan.dto.request.UserCreationRequest;
import hoc.tot.nhan.dto.request.UserUpdateRequest;
import hoc.tot.nhan.dto.response.UserResponse;
import hoc.tot.nhan.entity.User;
import hoc.tot.nhan.exception.AppException;
import hoc.tot.nhan.exception.ErrorCode;
import hoc.tot.nhan.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setMessage("success");
        response.setResult(userService.createUser(userCreationRequest));
        return response;
    }

    @PostMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setMessage("success");
        response.setResult(userService.updateUser(userId, userUpdateRequest));
        return response;
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String > deleteUser(@PathVariable String userId) {
        userService.deleteUserById(userId);
        return ApiResponse.<String>builder()
                .result("deleted user successfully")
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("username: {}", authentication.getName());
        authentication.getAuthorities().forEach(auth -> {log.info(auth.getAuthority());});

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();

    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {

        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }
}
