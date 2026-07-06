-- V4__create_client_sites_table.sql

CREATE TABLE client_sites (
    id                  BIGINT          GENERATED ALWAYS AS IDENTITY,
    organization_id     BIGINT          NOT NULL,
    client_id           BIGINT          NOT NULL,
    contract_id         BIGINT,
    name                VARCHAR(255)    NOT NULL,
    phone               VARCHAR(30),
    email               VARCHAR(255),
    address             VARCHAR(255)    NOT NULL,
    city                VARCHAR(100)    NOT NULL,
    province            VARCHAR(50)     NOT NULL,
    postal_code         VARCHAR(20)     NOT NULL,
    country             CHAR(2)         NOT NULL DEFAULT 'IT',
    building            VARCHAR(100),
    floor               VARCHAR(20),
    latitude            DECIMAL(10, 7),
    longitude           DECIMAL(10, 7),
    notes               TEXT,
    version             BIGINT          NOT NULL DEFAULT 0,
    created_at          TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at          TIMESTAMPTZ,

    CONSTRAINT pk_client_sites
        PRIMARY KEY (id),
    CONSTRAINT fk_client_sites_organization
        FOREIGN KEY (organization_id) REFERENCES organizations (id) ON DELETE RESTRICT,
    CONSTRAINT fk_client_sites_client
        FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE RESTRICT,
    CONSTRAINT fk_client_sites_contract
        FOREIGN KEY (contract_id) REFERENCES contracts (id) ON DELETE SET NULL,
    CONSTRAINT ck_client_sites_coordinates
        CHECK (
            (latitude IS NULL AND longitude IS NULL) OR
            (latitude IS NOT NULL AND longitude IS NOT NULL)
        )
);

CREATE INDEX idx_client_sites_organization_id ON client_sites (organization_id);
CREATE INDEX idx_client_sites_client_id       ON client_sites (client_id);
CREATE INDEX idx_client_sites_contract_id     ON client_sites (contract_id);