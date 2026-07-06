package com.manuel.safehub_api.controller;

import com.manuel.safehub_api.application.services.organization.OrganizationService;
import com.manuel.safehub_api.controller.dto.OrganizationRequest;
import com.manuel.safehub_api.controller.dto.OrganizationResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/organizations")
    public ResponseEntity<Page<OrganizationResponse>> getOrganizations(Pageable pageable){

        Page<OrganizationResponse> result = organizationService.getOrganizations(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/organizations/{id}")
    public ResponseEntity<OrganizationResponse> getOrganizationById(@PathVariable Long id){
        OrganizationResponse result = organizationService.getOrganizationById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/organizations")
    public ResponseEntity<OrganizationResponse> createOrganization(@RequestBody @Valid OrganizationRequest request) {
        OrganizationResponse result = organizationService.createOrganization(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
