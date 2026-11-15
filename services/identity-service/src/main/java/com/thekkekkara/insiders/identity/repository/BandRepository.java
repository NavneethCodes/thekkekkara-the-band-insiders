package com.thekkekkara.insiders.identity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.thekkekkara.insiders.identity.entity.BandEntity;

@Repository
public class BandRepository {

        private final NamedParameterJdbcTemplate jdbcTemplate;

        public BandRepository(NamedParameterJdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;
        }

        private final RowMapper<BandEntity> bandRowMapper = (rs, rowNum) -> new BandEntity(
                        rs.getLong("band_id"),
                        rs.getString("band_name"),
                        rs.getString("band_description"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime());

        public BandEntity save(BandEntity entity) {

                String sql = """
                                INSERT INTO bands (
                                    band_name,
                                    band_description
                                )
                                VALUES (
                                    :bandName,
                                    :bandDescription
                                )
                                RETURNING
                                    band_id,
                                    band_name,
                                    band_description,
                                    created_at,
                                    updated_at
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("bandName", entity.getBandNameString())
                                .addValue("bandDescription", entity.getBandDescriptionString());

                return jdbcTemplate.queryForObject(
                                sql,
                                parameters,
                                bandRowMapper);
        }

        public Optional<BandEntity> findById(Long bandIdLong) {

                String sql = """
                                SELECT
                                    band_id,
                                    band_name,
                                    band_description,
                                    created_at,
                                    updated_at
                                FROM bands
                                WHERE band_id = :bandId
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("bandId", bandIdLong);

                List<BandEntity> results = jdbcTemplate.query(
                                sql,
                                parameters,
                                bandRowMapper);

                return results.stream().findFirst();
        }

        public Optional<BandEntity> findByBandName(String bandNameString) {

                String sql = """
                                SELECT
                                    band_id,
                                    band_name,
                                    band_description,
                                    created_at,
                                    updated_at
                                FROM bands
                                WHERE band_name = :bandName
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("bandName", bandNameString);

                List<BandEntity> results = jdbcTemplate.query(
                                sql,
                                parameters,
                                bandRowMapper);

                return results.stream().findFirst();
        }

        public boolean existsByBandName(String bandNameString) {

                String sql = """
                                SELECT EXISTS (
                                    SELECT 1
                                    FROM bands
                                    WHERE band_name = :bandName
                                )
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("bandName", bandNameString);

                Boolean exists = jdbcTemplate.queryForObject(
                                sql,
                                parameters,
                                Boolean.class);

                return Boolean.TRUE.equals(exists);
        }

        public BandEntity update(BandEntity entity) {

                String sql = """
                                UPDATE bands
                                SET
                                    band_name = :bandName,
                                    band_description = :bandDescription,
                                    updated_at = CURRENT_TIMESTAMP
                                WHERE band_id = :bandId
                                RETURNING
                                    band_id,
                                    band_name,
                                    band_description,
                                    created_at,
                                    updated_at
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("bandId", entity.getBandIdLong())
                                .addValue("bandName", entity.getBandNameString())
                                .addValue("bandDescription", entity.getBandDescriptionString());

                return jdbcTemplate.queryForObject(
                                sql,
                                parameters,
                                bandRowMapper);
        }

        public void deleteById(Long bandIdLong) {

                String sql = """
                                DELETE FROM bands
                                WHERE band_id = :bandId
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("bandId", bandIdLong);

                jdbcTemplate.update(sql, parameters);
        }
}