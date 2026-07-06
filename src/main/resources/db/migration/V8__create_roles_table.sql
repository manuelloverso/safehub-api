-- V8__create_roles_table.sql

CREATE TABLE roles (
    id          BIGINT          GENERATED ALWAYS AS IDENTITY,
    name        VARCHAR(50)     NOT NULL,
    description TEXT,
    created_at  TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMPTZ     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_roles PRIMARY KEY (id),
    CONSTRAINT uk_roles_name UNIQUE (name)
);

INSERT INTO roles (name, description) VALUES
    ('ADMIN',       'Amministratore del tenant — accesso completo'),
    ('TECHNICIAN',  'Tecnico sul campo — accesso operativo');
