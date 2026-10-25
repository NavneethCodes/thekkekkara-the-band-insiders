package com.thekkekkara.insiders.identity.service;

import java.util.List;

import com.thekkekkara.insiders.identity.domain.Role;

public interface RoleService {
    Role createRole(Role role);

    Role getRoleById(Long roleIdLong);

    List<Role> getRolesByBandId(Long bandIdLong);

    Role getRoleByBandIdAndRoleName(Long bandIdLong, String roleNameString);

    void deleteRole(Long roleIdLong);
}