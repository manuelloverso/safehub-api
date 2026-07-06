package com.manuel.safehub_api.domain.client;


public class Client {

    private final Long id;
    private final Long organizationId;
    private final String name;
    private final String legalName;
    private final String vatNumber;
    private final String email;
    private final String phone;
    private final String notes;
    private final ClientStatus status;

    private Client(Long id, Long organizationId, String name, String legalName,
                   String vatNumber, String email, String phone, String notes,
                   ClientStatus status) {
        this.id = id;
        this.organizationId = organizationId;
        this.name = name;
        this.legalName = legalName;
        this.vatNumber = vatNumber;
        this.email = email;
        this.phone = phone;
        this.notes = notes;
        this.status = status;
    }

    /**
     * Creates a brand-new client. This is the only sanctioned way to bring a Client into
     * existence, and it enforces the creation invariants. The id is left null — the
     * database assigns it — and the status is fixed to {@link ClientStatus#ACTIVE}.
     */
    public static Client create(Long organizationId, String name, String legalName,
                                String vatNumber, String email, String phone, String notes) {
        if (organizationId == null) {
            throw new IllegalArgumentException("organizationId is required");
        }
        return new Client(
            null,
            organizationId,
            requireText(name, "name"),
            requireText(legalName, "legalName"),
            requireText(vatNumber, "vatNumber"),
            email,
            phone,
            notes,
            ClientStatus.ACTIVE
        );
    }

    /**
     * Rebuilds an existing client from persisted state. Used by the repository adapter when
     * reading rows back out. No invariant checks run here: the data was already valid when
     * it was stored, and re-validating on every load conflates "creating" with "loading".
     */
    public static Client reconstitute(Long id, Long organizationId, String name, String legalName,
                                      String vatNumber, String email, String phone, String notes,
                                      ClientStatus status) {
        return new Client(id, organizationId, name, legalName, vatNumber, email, phone, notes, status);
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value.trim();
    }

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
}
