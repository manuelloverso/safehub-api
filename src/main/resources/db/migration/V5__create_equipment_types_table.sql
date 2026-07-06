-- V5__create_equipment_types_table.sql

CREATE TABLE equipment_types (
     id                              BIGINT          GENERATED ALWAYS AS IDENTITY,
     code                            VARCHAR(100)    NOT NULL,
     name                            VARCHAR(255)    NOT NULL,
     category                        VARCHAR(50),
     extinguishing_agent             VARCHAR(50),
     regulatory_reference            VARCHAR(255),
     inspection_interval_months      SMALLINT,
     revision_interval_months        SMALLINT,
     test_interval_months            SMALLINT,
     homologation_validity_months    SMALLINT,
     max_service_life_months         SMALLINT,
     grace_period_days               SMALLINT,
     requires_certificate            BOOLEAN         NOT NULL DEFAULT FALSE,
     description                     TEXT,
     active                          BOOLEAN         NOT NULL DEFAULT TRUE,
     version                         BIGINT          NOT NULL DEFAULT 0,
     created_at                      TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at                      TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,

     CONSTRAINT pk_equipment_types
         PRIMARY KEY (id),
     CONSTRAINT uk_equipment_types_code
         UNIQUE (code),
     CONSTRAINT ck_equipment_types_category
         CHECK (category IN ('extinguisher', 'fire_door', 'hydrant', 'detector', 'fire_pump_group')),
     CONSTRAINT ck_equipment_types_agent
         CHECK (extinguishing_agent IN ('water', 'foam', 'CO2', 'powder', 'other')),
     CONSTRAINT ck_equipment_types_intervals
         CHECK (
             inspection_interval_months > 0 AND
             revision_interval_months > 0 AND
             test_interval_months > 0
         )
);

COMMENT ON COLUMN equipment_types.regulatory_reference        IS 'Riferimento normativo es. UNI 9994-1';
COMMENT ON COLUMN equipment_types.homologation_validity_months IS 'Durata omologazione in mesi';
COMMENT ON COLUMN equipment_types.max_service_life_months     IS 'Vita utile massima in mesi';
COMMENT ON COLUMN equipment_types.grace_period_days           IS 'Tolleranza scadenze in giorni';