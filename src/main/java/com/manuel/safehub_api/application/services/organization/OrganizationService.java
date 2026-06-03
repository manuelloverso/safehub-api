package com.manuel.safehub_api.application.services.organization;

import com.manuel.safehub_api.controller.dto.OrganizationRequest;
import com.manuel.safehub_api.controller.dto.OrganizationResponse;
import com.manuel.safehub_api.controller.exception.ResourceNotFoundException;
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
