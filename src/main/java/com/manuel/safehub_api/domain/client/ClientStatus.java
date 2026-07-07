package com.manuel.safehub_api.domain.client;

public enum ClientStatus {
    ACTIVE,
    INACTIVE,
    SUSPENDED;

    public String dbValue() {
        return name().toLowerCase();
    }

    public static ClientStatus fromDbValue(String value) {
        return ClientStatus.valueOf(value.toUpperCase());
    }
}
