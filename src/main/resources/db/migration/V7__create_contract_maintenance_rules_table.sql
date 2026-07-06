-- V7__create_contract_maintenance_rules_table.sql

CREATE TABLE contract_maintenance_rules (
    id                          BIGINT      GENERATED ALWAYS AS IDENTITY,
    organization_id             BIGINT      NOT NULL,
    contract_id                 BIGINT      NOT NULL,
    equipment_type_id           BIGINT      NOT NULL,
    inspection_interval_months  SMALLINT,
    revision_interval_months    SMALLINT,
    test_interval_months        SMALLINT,
    notes                       TEXT,
    version                     BIGINT      NOT NULL DEFAULT 0,
    created_at                  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at                  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at                  TIMESTAMPTZ,

    CONSTRAINT pk_contract_maintenance_rules
        PRIMARY KEY (id),
    CONSTRAINT fk_contract_maintenance_rules_organization
        FOREIGN KEY (organization_id) REFERENCES organizations (id) ON DELETE RESTRICT,
    CONSTRAINT fk_contract_maintenance_rules_contract
        FOREIGN KEY (contract_id) REFERENCES contracts (id) ON DELETE RESTRICT,
    CONSTRAINT fk_contract_maintenance_rules_equipment_type
        FOREIGN KEY (equipment_type_id) REFERENCES equipment_types (id) ON DELETE RESTRICT,
    CONSTRAINT uk_contract_maintenance_rules_type_per_contract
        UNIQUE (contract_id, equipment_type_id),
    CONSTRAINT ck_contract_maintenance_rules_intervals
        CHECK (
            inspection_interval_months > 0 AND
            revision_interval_months > 0 AND
            test_interval_months > 0
        )
);

CREATE INDEX idx_cmr_organization_id   ON contract_maintenance_rules (organization_id);
CREATE INDEX idx_cmr_equipment_type_id ON contract_maintenance_rules (equipment_type_id);
