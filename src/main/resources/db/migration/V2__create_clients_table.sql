CREATE TABLE clients (
    id                  BIGINT          GENERATED ALWAYS AS IDENTITY,
    organization_id     BIGINT          NOT NULL,
    name                VARCHAR(255)    NOT NULL,
    legal_name          VARCHAR(255)    NOT NULL,
    vat_number          VARCHAR(50),
    email               VARCHAR(255),
    phone               VARCHAR(30),
    notes               TEXT,
    status              VARCHAR(30)     NOT NULL DEFAULT 'active',
    version             BIGINT          NOT NULL DEFAULT 0,
    created_at          TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at          TIMESTAMPTZ,

    CONSTRAINT pk_clients
       PRIMARY KEY (id),
    CONSTRAINT fk_clients_organization
       FOREIGN KEY (organization_id) REFERENCES organizations (id) ON DELETE RESTRICT,
    CONSTRAINT uk_clients_email_per_tenant
       UNIQUE (organization_id, email),
    CONSTRAINT uk_clients_vat_per_tenant
       UNIQUE (organization_id, vat_number),
    CONSTRAINT ck_clients_status
       CHECK (status IN ('active', 'inactive', 'suspended'))
);