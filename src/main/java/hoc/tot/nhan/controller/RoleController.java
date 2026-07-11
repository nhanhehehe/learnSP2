package hoc.tot.nhan.controller;

import hoc.tot.nhan.dto.request.ApiResponse;
import hoc.tot.nhan.dto.request.RoleRequest;
import hoc.tot.nhan.dto.response.RoleResponse;
import hoc.tot.nhan.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(roleRequest))
                .build();
    }

    @DeleteMapping("/{request}")
    ApiResponse<String> deleteRole(@PathVariable String request) {
        roleService.delete(request);
        return ApiResponse.<String>builder()
                .result("role deleted successfully")
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRole())
                .build();
    }
}
