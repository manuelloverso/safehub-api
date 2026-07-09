package com.manuel.safehub_api.infrastructure.jpa.specifications;

import com.manuel.safehub_api.infrastructure.jpa.entities.ClientJpaEntity;
import org.springframework.data.jpa.domain.Specification;

public final class ClientSpecifications {

    private ClientSpecifications() {
    }

    public static Specification<ClientJpaEntity> belongsToOrganization(Long organizationId) {
        return (root, query, cb) -> cb.equal(root.get("organizationId"), organizationId);
    }

    public static Specification<ClientJpaEntity> notDeleted() {
        return (root, query, cb) -> cb.isNull(root.get("deletedAt"));
    }

    public static Specification<ClientJpaEntity> hasName(String name) {
        return (root, query, cb) ->
            name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<ClientJpaEntity> hasVatNumber(String vatNumber) {
        return (root, query, cb) ->
            vatNumber == null ? null : cb.equal(root.get("vatNumber"), vatNumber);
    }
}
