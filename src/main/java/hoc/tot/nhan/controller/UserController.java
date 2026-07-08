package hoc.tot.nhan.controller;

import hoc.tot.nhan.dto.request.ApiResponse;
import hoc.tot.nhan.dto.request.UserCreationRequest;
import hoc.tot.nhan.dto.request.UserUpdateRequest;
import hoc.tot.nhan.dto.response.UserResponse;
import hoc.tot.nhan.entity.User;
import hoc.tot.nhan.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
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
    void deletedUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setMessage("success");
        response.setResult(userService.getAllUsers());
        return response;

    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUserById(@PathVariable String userId) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setMessage("success");
        response.setResult(userService.getUserById(userId));
        return response;
    }
}
