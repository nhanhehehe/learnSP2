package hoc.tot.nhan.mapper;

import hoc.tot.nhan.dto.request.RoleRequest;
import hoc.tot.nhan.dto.response.RoleResponse;
import hoc.tot.nhan.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role role);
}
