package hoc.tot.nhan.controller;

import hoc.tot.nhan.dto.request.ApiResponse;
import hoc.tot.nhan.dto.request.UserCreationRequest;
import hoc.tot.nhan.dto.request.UserUpdateRequest;
import hoc.tot.nhan.entity.User;
import hoc.tot.nhan.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setMessage("success");
        response.setResult(userService.createUser(userCreationRequest));
        return response;
    }

    @PostMapping("/{userId}")
    ApiResponse<User> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setMessage("success");
        response.setResult(userService.updateUser(userId, userUpdateRequest));
        return response;
    }

    @DeleteMapping("/{userId}")
    void deletedUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
    }

    @GetMapping
    ApiResponse<List<User>> getAllUsers() {
        ApiResponse<List<User>> response = new ApiResponse<>();
        response.setMessage("success");
        response.setResult(userService.getAllUsers());
        return response;

    }

    @GetMapping("/{userId}")
    ApiResponse<User> getUserById(@PathVariable String userId) {
        ApiResponse<User> response = new ApiResponse<>();
        response.setMessage("success");
        response.setResult(userService.getUserById(userId));
        return response;
    }
}
