package com.manuel.safehub_api.application.services.organization;

import com.manuel.safehub_api.controller.dto.OrganizationResponse;
import com.manuel.safehub_api.infrastructure.jpa.entities.OrganizationJpaEntity;
import com.manuel.safehub_api.infrastructure.jpa.repositories.OrganizationJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    private final OrganizationJpaRepository organizationJpaRepository;

    public OrganizationService(OrganizationJpaRepository organizationJpaRepository) {
        this.organizationJpaRepository = organizationJpaRepository;
    }

    public List<OrganizationResponse> getOrganizations(){
        return organizationJpaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private OrganizationResponse toResponse(OrganizationJpaEntity organizationJpaEntity){
        return new OrganizationResponse(
            organizationJpaEntity.getId(),
            organizationJpaEntity.getName(),
            organizationJpaEntity.getEmail()
        );
    }
}
