CREATE TABLE IF NOT EXISTS band_memberships (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    band_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_membership_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT fk_membership_band FOREIGN KEY (band_id) REFERENCES bands(band_id) ON DELETE CASCADE,
    CONSTRAINT fk_membership_role FOREIGN KEY (role_id) REFERENCES roles(role_id),
    CONSTRAINT uq_user_band UNIQUE (user_id, band_id, role_id)
)