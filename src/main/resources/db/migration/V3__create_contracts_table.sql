-- V3__create_contracts_table.sql

CREATE TABLE contracts (
    id                  BIGINT          GENERATED ALWAYS AS IDENTITY,
    organization_id     BIGINT          NOT NULL,
    client_id           BIGINT          NOT NULL,
    contract_number     VARCHAR(100)    NOT NULL,
    name                VARCHAR(255)    NOT NULL,
    start_date          DATE,
    end_date            DATE,
    status              VARCHAR(30)     NOT NULL,
    terms_conditions    TEXT,
    notes               TEXT,
    version             BIGINT          NOT NULL DEFAULT 0,
    created_at          TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at          TIMESTAMPTZ,

    CONSTRAINT pk_contracts
       PRIMARY KEY (id),
    CONSTRAINT fk_contracts_organization
       FOREIGN KEY (organization_id) REFERENCES organizations (id) ON DELETE RESTRICT,
    CONSTRAINT fk_contracts_client
       FOREIGN KEY (client_id) REFERENCES clients (id) ON DELETE RESTRICT,
    CONSTRAINT uk_contracts_number_per_tenant
       UNIQUE (organization_id, contract_number),
    CONSTRAINT ck_contracts_status
       CHECK (status IN ('active', 'expired', 'cancelled', 'suspended', 'draft')),
    CONSTRAINT ck_contracts_dates
       CHECK (end_date IS NULL OR start_date IS NULL OR end_date > start_date)
);

CREATE INDEX idx_contracts_client_id ON contracts (client_id);