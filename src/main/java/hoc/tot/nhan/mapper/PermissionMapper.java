package hoc.tot.nhan.mapper;

import ch.qos.logback.core.model.ComponentModel;
import hoc.tot.nhan.dto.request.PermissionRequest;
import hoc.tot.nhan.dto.response.PermissionResponse;
import hoc.tot.nhan.entity.Permission;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission entity);
}
