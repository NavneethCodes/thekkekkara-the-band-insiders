package com.thekkekkara.insiders.identity.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import com.thekkekkara.insiders.identity.entity.RoleEntity;

@Repository
public class RoleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RoleRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<RoleEntity> roleRowMapper = (rs, rowNum) -> new RoleEntity(
            rs.getLong("role_id"),
            rs.getLong("band_id"),
            rs.getString("role_name"),
            rs.getString("role_description"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime());

    public Optional<RoleEntity> findById(Long roleIdLong) {

        String sql = """
                SELECT role_id, band_id, role_name, role_description, created_at, updated_at FROM roles WHERE role_id = :roleId
                """;
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("roleId", roleIdLong);
        List<RoleEntity> results = jdbcTemplate.query(sql, parameters, roleRowMapper);

        return results.stream().findFirst();
    }

    public List<RoleEntity> findByBandId(long bandIdLong) {
        String sql = """
                SELECT role_id, band_id, role_name, role_description, created_at, updated_at FROM roles WHERE band_id = :bandId
                """;
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("bandId", bandIdLong);
        return jdbcTemplate.query(sql, parameters, roleRowMapper);
    }

    public Optional<RoleEntity> findByBandIdAndRoleName(Long bandIdLong, String roleNameString) {
        String sql = """
                SELECT role_id, band_id, role_name, role_description, created_at, updated_at FROM roles WHERE band_id = :bandId AND role_name = :roleName
                """;
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("bandId", bandIdLong)
                .addValue("roleName", roleNameString);

        List<RoleEntity> results = jdbcTemplate.query(sql, parameters, roleRowMapper);

        return results.stream().findFirst();
    }

    public RoleEntity save(RoleEntity entity) {
        String sql = """
                INSERT INTO roles (band_id, role_name, role_description)
                VALUES (:bandId, :roleName, :roleDescription)
                RETURNING role_id, band_id, role_name, role_description, created_at, updated_at
                """;
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("bandId", entity.getBandIdLong())
                .addValue("roleName", entity.getRoleNameString())
                .addValue("roleDescription", entity.getRoleDescriptionString());

        return jdbcTemplate.queryForObject(sql, parameters, roleRowMapper);
    }

    public boolean existsById(Long roleIdLong) {
        String sql = """
                SELECT EXISTS(SELECT 1 FROM roles WHERE role_id = :roleId)
                """;
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("roleId", roleIdLong);

        Boolean exists = jdbcTemplate.queryForObject(
                sql,
                parameters,
                Boolean.class);

        return Boolean.TRUE.equals(exists);
    }

    public boolean existsByBandIdAndRoleName(
            Long bandIdLong,
            String roleNameString) {

        String sql = """
                SELECT EXISTS (
                    SELECT 1
                    FROM roles
                    WHERE band_id = :bandId
                      AND role_name = :roleName
                )
                """;

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("bandId", bandIdLong)
                .addValue("roleName", roleNameString);

        Boolean exists = jdbcTemplate.queryForObject(
                sql,
                parameters,
                Boolean.class);

        return Boolean.TRUE.equals(exists);
    }

    public void deleteById(Long roleIdLong) {

        String sql = """
                DELETE FROM roles
                WHERE role_id = :roleId
                """;

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("roleId", roleIdLong);

        jdbcTemplate.update(sql, parameters);
    }

}
