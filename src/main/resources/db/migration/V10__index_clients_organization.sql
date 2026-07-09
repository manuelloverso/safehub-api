CREATE INDEX idx_clients_organization_active
    ON clients (organization_id, name)
    WHERE deleted_at IS NULL;