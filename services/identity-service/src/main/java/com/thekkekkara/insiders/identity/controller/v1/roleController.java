
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
import com.thekkekkara.insiders.identity.exception.ResourceNotFoundException;
import com.thekkekkara.insiders.identity.repository.mapper.RoleRowMapper;
import com.thekkekkara.insiders.identity.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bands/{bandId}/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleRowMapper mapper;

    public RoleController(
            RoleService roleService,
            RoleRowMapper mapper) {
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse createRole(
            @PathVariable("bandId") Long bandIdLong,
            @Valid @RequestBody CreateRoleRequest request) {

        Role role = this.mapper.toDomain(request);

        role = new Role(
                role.getRoleIdLong(),
                bandIdLong,
                role.getRoleNameString(),
                role.getRoleDescriptionString(),
                role.getCreatedAtLocalDateTime(),
                role.getUpdatedAtLocalDateTime());

        Role createdRole = this.roleService.createRole(role);

        return this.mapper.toResponse(createdRole);
    }

    @GetMapping
    public List<RoleResponse> getRolesByBandId(
            @PathVariable("bandId") Long bandIdLong) {

        List<RoleResponse> roles = this.roleService
                .getRolesByBandId(bandIdLong)
                .stream()
                .map(this.mapper::toResponse)
                .toList();

        if (roles.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No roles attached to band ID: " + bandIdLong);
        }

        return roles;
    }

    @GetMapping("/{roleIdLong}")
    public RoleResponse getRoleById(
            @PathVariable("bandId") Long bandIdLong,
            @PathVariable("roleIdLong") Long roleIdLong) {

        Role role = findRoleById(roleIdLong);

        if (!role.getBandIdLong().equals(bandIdLong)) {
            throw new ResourceNotFoundException(
                    "No role found with role ID: " + roleIdLong
                            + " attached to band ID: " + bandIdLong);
        }

        return this.mapper.toResponse(role);
    }

    @GetMapping("/name/{roleNameString}")
    public RoleResponse getRoleByBandIdAndRoleName(
            @PathVariable("bandId") Long bandIdLong,
            @PathVariable("roleNameString") String roleNameString) {

        Role role;

        try {
            role = this.roleService.getRoleByBandIdAndRoleName(
                    bandIdLong,
                    roleNameString);
        } catch (IllegalArgumentException exception) {
            throw new ResourceNotFoundException(
                    "No role named " + roleNameString
                            + " attached to band ID: " + bandIdLong);
        }

        return this.mapper.toResponse(role);
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(
            @PathVariable("bandId") Long bandIdLong,
            @PathVariable("roleId") Long roleId) {

        Role role = findRoleById(roleId);

        if (!role.getBandIdLong().equals(bandIdLong)) {
            throw new ResourceNotFoundException(
                    "No role found with role ID: " + roleId
                            + " attached to band ID: " + bandIdLong);
        }

        this.roleService.deleteRole(roleId);
    }

    private Role findRoleById(Long roleIdLong) {
        try {
            return this.roleService.getRoleById(roleIdLong);
        } catch (IllegalArgumentException exception) {
            throw new ResourceNotFoundException(
                    "No role found with role ID: " + roleIdLong);
        }
    }
}
