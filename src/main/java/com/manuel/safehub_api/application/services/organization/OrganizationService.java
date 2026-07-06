package com.manuel.safehub_api.application.services.organization;

import com.manuel.safehub_api.controller.dto.organization.OrganizationRequest;
import com.manuel.safehub_api.controller.dto.organization.OrganizationResponse;
import com.manuel.safehub_api.domain.exception.ResourceNotFoundException;
import com.manuel.safehub_api.infrastructure.jpa.entities.OrganizationJpaEntity;
import com.manuel.safehub_api.infrastructure.jpa.repositories.OrganizationJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    private final OrganizationJpaRepository organizationJpaRepository;

    public OrganizationService(OrganizationJpaRepository organizationJpaRepository) {
        this.organizationJpaRepository = organizationJpaRepository;
    }

    public Page<OrganizationResponse> getOrganizations(Pageable pageable){
        return organizationJpaRepository.findAll(pageable)
                .map(this::toResponse);
    }

    public OrganizationResponse getOrganizationById(Long id){
        return organizationJpaRepository.findById(id).
                map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + id));
    }

    public OrganizationResponse createOrganization(OrganizationRequest request) {
        OrganizationJpaEntity organizationJpaEntity = new OrganizationJpaEntity();
        organizationJpaEntity.setName(request.getName());
        organizationJpaEntity.setEmail(request.getEmail());

        OrganizationJpaEntity result = organizationJpaRepository.save(organizationJpaEntity);
        return toResponse(result);
    }

    private OrganizationResponse toResponse(OrganizationJpaEntity organizationJpaEntity){
        return new OrganizationResponse(
            organizationJpaEntity.getId(),
            organizationJpaEntity.getName(),
            organizationJpaEntity.getEmail()
        );
    }
}
