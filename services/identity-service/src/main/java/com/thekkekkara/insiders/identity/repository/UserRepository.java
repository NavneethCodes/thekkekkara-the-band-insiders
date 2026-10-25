
package com.thekkekkara.insiders.identity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.thekkekkara.insiders.identity.entity.UserEntity;

@Repository
public class UserRepository {

        private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

        private final RowMapper<UserEntity> userRowMapper = (resultSet, rowNumber) -> new UserEntity(
                        resultSet.getObject("user_id", Long.class),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        null,
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getBoolean("enabled"),
                        resultSet.getBoolean("account_locked"),
                        resultSet.getTimestamp("created_at").toLocalDateTime(),
                        resultSet.getTimestamp("updated_at").toLocalDateTime());

        private final RowMapper<UserEntity> authenticationRowMapper = (resultSet, rowNumber) -> new UserEntity(
                        resultSet.getObject("user_id", Long.class),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password_hash"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getBoolean("enabled"),
                        resultSet.getBoolean("account_locked"),
                        resultSet.getTimestamp("created_at").toLocalDateTime(),
                        resultSet.getTimestamp("updated_at").toLocalDateTime());

        public UserRepository(
                        NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
                this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        }

        public Optional<UserEntity> findById(Long userIdLong) {
                String sql = """
                                SELECT user_id, username, email,
                                       first_name, last_name, enabled, account_locked,
                                       created_at, updated_at
                                FROM users
                                WHERE user_id = :userIdLong
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("userIdLong", userIdLong);

                List<UserEntity> users = this.namedParameterJdbcTemplate.query(
                                sql, parameters, userRowMapper);

                return users.stream().findFirst();
        }

        public Optional<UserEntity> findByUsername(String usernameString) {
                String sql = """
                                SELECT user_id, username, email,
                                       first_name, last_name, enabled, account_locked,
                                       created_at, updated_at
                                FROM users
                                WHERE username = :usernameString
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("usernameString", usernameString);

                List<UserEntity> users = this.namedParameterJdbcTemplate.query(
                                sql, parameters, userRowMapper);

                return users.stream().findFirst();
        }

        public Optional<UserEntity> findByEmail(String emailString) {
                String sql = """
                                SELECT user_id, username, email,
                                       first_name, last_name, enabled, account_locked,
                                       created_at, updated_at
                                FROM users
                                WHERE email = :emailString
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("emailString", emailString);

                List<UserEntity> users = this.namedParameterJdbcTemplate.query(
                                sql, parameters, userRowMapper);

                return users.stream().findFirst();
        }

        public Optional<UserEntity> findForAuthenticationByUsername(
                        String usernameString) {

                String sql = """
                                SELECT user_id, username, email, password_hash,
                                       first_name, last_name, enabled, account_locked,
                                       created_at, updated_at
                                FROM users
                                WHERE username = :usernameString
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("usernameString", usernameString);

                List<UserEntity> users = this.namedParameterJdbcTemplate.query(
                                sql, parameters, authenticationRowMapper);

                return users.stream().findFirst();
        }

        public boolean existsByUsername(String usernameString) {
                String sql = """
                                SELECT EXISTS (
                                    SELECT 1
                                    FROM users
                                    WHERE username = :usernameString
                                )
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("usernameString", usernameString);

                Boolean exists = this.namedParameterJdbcTemplate.queryForObject(
                                sql, parameters, Boolean.class);

                return Boolean.TRUE.equals(exists);
        }

        public boolean existsByEmail(String emailString) {
                String sql = """
                                SELECT EXISTS (
                                    SELECT 1
                                    FROM users
                                    WHERE email = :emailString
                                )
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("emailString", emailString);

                Boolean exists = this.namedParameterJdbcTemplate.queryForObject(
                                sql, parameters, Boolean.class);

                return Boolean.TRUE.equals(exists);
        }

        public boolean existsByUserId(Long userIdLong) {
                String sql = """
                                SELECT EXISTS(SELECT 1 FROM users WHERE user_id = :userId)
                                """;
                MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("userId", userIdLong);
                Boolean exists = this.namedParameterJdbcTemplate.queryForObject(sql, parameters, Boolean.class);
                return exists;
        }

        public UserEntity save(UserEntity userEntity) {
                String sql = """
                                INSERT INTO users (
                                    username, email, password_hash,
                                    first_name, last_name, enabled, account_locked
                                )
                                VALUES (
                                    :usernameString, :emailString, :passwordHashString,
                                    :firstNameString, :lastNameString,
                                    :enabledBoolean, :accountLockedBoolean
                                )
                                RETURNING user_id, username, email,
                                          first_name, last_name, enabled, account_locked,
                                          created_at, updated_at
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("usernameString", userEntity.getUsernameString())
                                .addValue("emailString", userEntity.getEmailString())
                                .addValue("passwordHashString", userEntity.getPasswordHashString())
                                .addValue("firstNameString", userEntity.getFirstNameString())
                                .addValue("lastNameString", userEntity.getLastNameString())
                                .addValue("enabledBoolean", userEntity.getEnabledBoolean())
                                .addValue("accountLockedBoolean", userEntity.getAccountLockedBoolean());

                return this.namedParameterJdbcTemplate.queryForObject(
                                sql, parameters, userRowMapper);
        }

        public void deleteById(Long userIdLong) {
                String sql = """
                                DELETE FROM users
                                WHERE user_id = :userIdLong
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("userIdLong", userIdLong);

                this.namedParameterJdbcTemplate.update(sql, parameters);
        }

        public boolean isPlatformAdminByUserId(Long userIdLong) {
                String sql = """
                                SELECT is_platform_admin
                                FROM users
                                WHERE user_id = :userIdLong
                                """;

                MapSqlParameterSource parameters = new MapSqlParameterSource()
                                .addValue("userIdLong", userIdLong);

                Boolean isPlatformAdmin = this.namedParameterJdbcTemplate.queryForObject(
                                sql,
                                parameters,
                                Boolean.class);

                return Boolean.TRUE.equals(isPlatformAdmin);
        }
}
