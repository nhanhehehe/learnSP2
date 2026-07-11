package hoc.tot.nhan.service;

import hoc.tot.nhan.dto.request.RoleRequest;
import hoc.tot.nhan.dto.response.RoleResponse;
import hoc.tot.nhan.entity.Permission;
import hoc.tot.nhan.entity.Role;
import hoc.tot.nhan.mapper.RoleMapper;
import hoc.tot.nhan.repository.PermissionRepository;
import hoc.tot.nhan.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponse create(RoleRequest roleRequest) {
        var role = roleMapper.toRole(roleRequest);

        var permissions = permissionRepository.findAllById(roleRequest.getPermissions());

        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    public void delete(String request) {
        roleRepository.deleteById(request);
    }

    public List<RoleResponse> getAllRole() {
          return roleRepository.findAll()
                  .stream()
                  .map(roleMapper::toRoleResponse)
                  .toList();
    }
}
