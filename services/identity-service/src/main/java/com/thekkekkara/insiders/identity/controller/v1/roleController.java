package com.thekkekkara.insiders.identity.controller.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.thekkekkara.insiders.identity.domain.Role;
import com.thekkekkara.insiders.identity.dto.role.CreateRoleRequest;
import com.thekkekkara.insiders.identity.dto.role.RoleResponse;
import com.thekkekkara.insiders.identity.repository.mapper.RoleRowMapper;
import com.thekkekkara.insiders.identity.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bands/{bandId}/roles")
public class roleController {

    private final RoleService roleService;
    private final RoleRowMapper mapper;

    public roleController(RoleService roleService, RoleRowMapper mapper) {
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse createRole(
            @PathVariable Long bandIdLong,
            @Valid @RequestBody CreateRoleRequest request) {
        Role role = mapper.toDomain(request);
        role = new Role(
                role.getRoleIdLong(),
                bandIdLong,
                role.getRoleNameString(),
                role.getRoleDescriptionString(),
                role.getCreatedAtLocalDateTime(),
                role.getUpdatedAtLocalDateTime());

        Role createdRole = roleService.createRole(role);

        return mapper.toResponse(createdRole);
    }

    @GetMapping
    public List<RoleResponse> getRolesByBandId(
            @PathVariable Long BandIdLong) {
        return roleService.getRolesByBandId(BandIdLong)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{roleIdLong}")
    public RoleResponse getRoleById(
            @PathVariable Long bandIdLong,
            @PathVariable Long roleIdLong) {
        Role role = roleService.getRoleById(roleIdLong);
        if (!role.getBandIdLong().equals(bandIdLong)) {
            throw new IllegalArgumentException(
                    "Role does not belong to the specified band");
        }
        return mapper.toResponse(role);
    }

    @GetMapping("name/{roleNameString}")
    public RoleResponse getRoleByBandIdAndRoleName(
            @PathVariable Long bandIdLong,
            @PathVariable String roleNameString) {
        Role role = roleService.getRoleByBandIdAndRoleName(bandIdLong, roleNameString);
        return mapper.toResponse(role);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(
            @PathVariable Long bandIdLong,
            @PathVariable Long roleId) {
        Role role = roleService.getRoleById(roleId);
        if (!role.getBandIdLong().equals(bandIdLong)) {
            throw new IllegalArgumentException(
                    "Role does not belong to the band!");
        }
        roleService.deleteRole(roleId);
    }
}
