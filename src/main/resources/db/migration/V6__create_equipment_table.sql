-- V6__create_equipment_table.sql

CREATE TABLE equipment (
    id                      BIGINT          GENERATED ALWAYS AS IDENTITY,
    organization_id         BIGINT          NOT NULL,
    client_id               BIGINT          NOT NULL,
    client_site_id        BIGINT          NOT NULL,
    equipment_type_id       BIGINT          NOT NULL,
    serial_number           VARCHAR(100),
    internal_code           VARCHAR(100),
    model                   VARCHAR(255),
    location                VARCHAR(255),
    floor                   VARCHAR(20),
    status                  VARCHAR(30)     NOT NULL DEFAULT 'active',
    manufacturing_date      DATE,
    installation_date       DATE,
    last_inspection_date    DATE,
    last_revision_date      DATE,
    last_test_date          DATE,
    notes                   TEXT,
    version                 BIGINT          NOT NULL DEFAULT 0,
    created_at              TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at              TIMESTAMPTZ,

    CONSTRAINT pk_equipment
       PRIMARY KEY (id),
    CONSTRAINT fk_equipment_organization
       FOREIGN KEY (organization_id) REFERENCES organizations (id) ON DELETE RESTRICT,
    CONSTRAINT fk_equipment_client
        FOREIGN KEY (client_id) references clients (id) ON DELETE RESTRICT,
    CONSTRAINT fk_equipment_client_site
       FOREIGN KEY (client_site_id) REFERENCES client_sites (id) ON DELETE RESTRICT,
    CONSTRAINT fk_equipment_type
       FOREIGN KEY (equipment_type_id) REFERENCES equipment_types (id) ON DELETE RESTRICT,
    CONSTRAINT ck_equipment_status
       CHECK (status IN ('active', 'inactive', 'dismissed', 'under_maintenance')),
    CONSTRAINT uk_equipment_serial_per_tenant
       UNIQUE (organization_id, serial_number)
);

COMMENT ON COLUMN equipment.manufacturing_date   IS 'Data di fabbricazione';

CREATE INDEX idx_equipment_client_id      ON equipment (client_id);
CREATE INDEX idx_equipment_client_site_id ON equipment (client_site_id);
CREATE INDEX idx_equipment_type_id        ON equipment (equipment_type_id);