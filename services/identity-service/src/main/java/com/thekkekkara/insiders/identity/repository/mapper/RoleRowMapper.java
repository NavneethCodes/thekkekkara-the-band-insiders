package com.thekkekkara.insiders.identity.repository.mapper;

import org.springframework.stereotype.Component;

import com.thekkekkara.insiders.identity.domain.Role;
import com.thekkekkara.insiders.identity.dto.role.CreateRoleRequest;
import com.thekkekkara.insiders.identity.dto.role.RoleResponse;
import com.thekkekkara.insiders.identity.entity.RoleEntity;

@Component
public class RoleRowMapper {

    public Role toDomain(CreateRoleRequest request) {
        return new Role(
                null,
                request.bandIDLong(),
                request.roleNameString(),
                request.roleDescriptionString(),
                null,
                null);
    }

    public Role toDomain(RoleEntity entity) {
        return new Role(
                entity.getRoleIdLong(),
                entity.getBandIdLong(),
                entity.getRoleNameString(),
                entity.getRoleDescriptionString(),
                entity.getCreatedAtLocalDateTime(),
                entity.getUpdatedAtLocalDateTime());
    }

    public RoleEntity toEntity(Role role) {
        return new RoleEntity(
                role.getRoleIdLong(),
                role.getBandIdLong(),
                role.getRoleNameString(),
                role.getRoleDescriptionString(),
                role.getCreatedAtLocalDateTime(),
                role.getUpdatedAtLocalDateTime());
    }

    public RoleResponse toResponse(Role role) {
        return new RoleResponse(
                role.getRoleIdLong(),
                role.getBandIdLong(),
                role.getRoleNameString(),
                role.getRoleDescriptionString(),
                role.getCreatedAtLocalDateTime(),
                role.getUpdatedAtLocalDateTime());
    }
}
