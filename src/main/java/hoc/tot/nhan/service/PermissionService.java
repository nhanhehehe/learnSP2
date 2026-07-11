package hoc.tot.nhan.service;

import hoc.tot.nhan.dto.request.PermissionRequest;
import hoc.tot.nhan.dto.response.PermissionResponse;
import hoc.tot.nhan.entity.Permission;
import hoc.tot.nhan.mapper.PermissionMapper;
import hoc.tot.nhan.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class PermissionService {
    PermissionMapper permissionMapper;
    PermissionRepository permissionRepository;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    public void delete(String request) {
        permissionRepository.deleteById(request);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();

        List<PermissionResponse> permissionResponse = new ArrayList<>();

        permissions.forEach(permission ->
                permissionResponse.add(permissionMapper.toPermissionResponse(permission)));

        return permissionResponse;
    }
}
