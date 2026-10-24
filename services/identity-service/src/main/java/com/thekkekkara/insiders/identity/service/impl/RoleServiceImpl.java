package com.thekkekkara.insiders.identity.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.thekkekkara.insiders.identity.domain.Role;
import com.thekkekkara.insiders.identity.entity.RoleEntity;
import com.thekkekkara.insiders.identity.repository.RoleRepository;
import com.thekkekkara.insiders.identity.repository.mapper.RoleRowMapper;
import com.thekkekkara.insiders.identity.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final RoleRowMapper mapper;

    public RoleServiceImpl(RoleRepository repository, RoleRowMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Role createRole(Role role) {
        if (this.repository.existsByBandIdAndRoleName(role.getBandIdLong(), role.getRoleNameString())) {
            throw new IllegalArgumentException("Role Already exists for this band!");
        }
        RoleEntity entity = this.mapper.toEntity(role);
        RoleEntity saveEntity = this.repository.save(entity);
        return this.mapper.toDomain(saveEntity);
    }

    @Override
    public Role getRoleById(Long roleIdLong) {

        return this.repository.findById(roleIdLong)
                .map(this.mapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Role not found with id" + roleIdLong));
    }

    @Override
    public List<Role> getRolesByBandId(Long bandIdLong) {

        return this.repository.findByBandId(bandIdLong)
                .stream()
                .map(this.mapper::toDomain)
                .toList();
    }

    @Override
    public Role getRoleByBandIdAndRoleName(Long bandIdLong, String roleNameString) {
        return this.repository.findByBandIdAndRoleName(bandIdLong, roleNameString)
                .map(this.mapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Role not found for the band!"));
    }

    @Override
    public void deleteRole(Long roleIdLong) {
        if (!this.repository.existsById(roleIdLong)) {
            throw new IllegalArgumentException(
                    "Role not found with id: " + roleIdLong);
        }
        this.repository.deleteById(roleIdLong);
    }
}
