CREATE TABLE IF NOT EXISTS roles(
    role_id BIGSERIAL PRIMARY KEY,
    band_id BIGINT NOT NULL,
    role_name VARCHAR(50) NOT NULL,
    role_description VARCHAR(50),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_role_band FOREIGN KEY (band_id) REFERENCES bands(band_id),
    CONSTRAINT uq_band_role_name UNIQUE(band_id, role_name)
);