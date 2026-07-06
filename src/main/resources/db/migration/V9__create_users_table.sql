-- V9__create_users_table.sql

CREATE TABLE users (
    id                  BIGINT          GENERATED ALWAYS AS IDENTITY,
    organization_id     BIGINT          NOT NULL,
    role_id             BIGINT          NOT NULL,
    name                VARCHAR(255)    NOT NULL,
    email               VARCHAR(255)    NOT NULL,
    email_verified_at   TIMESTAMPTZ,
    password_hash       VARCHAR(255)    NOT NULL,
    phone               VARCHAR(30),
    version             BIGINT          NOT NULL DEFAULT 0,
    created_at          TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at          TIMESTAMPTZ,

    CONSTRAINT pk_users
       PRIMARY KEY (id),
    CONSTRAINT fk_users_organization
       FOREIGN KEY (organization_id) REFERENCES organizations (id) ON DELETE RESTRICT,
    CONSTRAINT fk_users_role
       FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE RESTRICT,
    CONSTRAINT uk_users_email
       UNIQUE (email)
);

-- FK indexes. uk_users_email only indexes (email), so both FKs need their own.
CREATE INDEX idx_users_organization_id ON users (organization_id);
CREATE INDEX idx_users_role_id         ON users (role_id);