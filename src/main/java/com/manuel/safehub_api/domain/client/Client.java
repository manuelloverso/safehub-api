package com.manuel.safehub_api.domain.client;


import java.util.Objects;

public class Client {

    private Long id;
    private final Long organizationId;
    private final String name;
    private final String legalName;
    private final String vatNumber;
    private final String email;
    private final String phone;
    private final String notes;
    private final ClientStatus status;

    private Client(
        Long id,
        Long organizationId,
        String name,
        String legalName,
        String vatNumber,
        String email,
        String phone,
        String notes,
        ClientStatus status
    ) {
        this.id = id;
        this.organizationId = Objects.requireNonNull(organizationId, "organizationId is required");
        this.name = Objects.requireNonNull(name, "name is required");
        this.legalName = Objects.requireNonNull(legalName, "legalName is required");
        this.vatNumber = vatNumber;
        this.email = email;
        this.phone = phone;
        this.notes = notes;
        this.status = Objects.requireNonNull(status, "status is required");
    }

    public static Client create(
        Long organizationId,
        String name,
        String legalName,
        String vatNumber,
        String email,
        String phone,
        String notes
    ) {
        if (organizationId == null) {
            throw new IllegalArgumentException("organizationId is required");
        }
        return new Client(
            null,
            organizationId,
            name,
            legalName,
            vatNumber,
            email,
            phone,
            notes,
            ClientStatus.ACTIVE
        );
    }

    public static Client reconstitute(
        Long id,
        Long organizationId,
        String name,
        String legalName,
        String vatNumber,
        String email,
        String phone,
        String notes,
        ClientStatus status
    ) {
        return new Client(
            id,
            organizationId,
            name,
            legalName,
            vatNumber,
            email,
            phone,
            notes,
            status
        );
    }

    //GETTERS
    public Long getId() {
        return id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public String getName() {
        return name;
    }

    public String getLegalName() {
        return legalName;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getNotes() {
        return notes;
    }

    public ClientStatus getStatus() {
        return status;
    }

    //SETTERS
    public void setId(Long id) {
        this.id = id;
    }
}
