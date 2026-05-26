package com.manuel.safehub_api.controller;

import com.manuel.safehub_api.application.services.organization.OrganizationService;
import com.manuel.safehub_api.controller.dto.OrganizationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/organizations")
    public List<OrganizationResponse> getOrganizations(){
        return organizationService.getOrganizations();
    }
}
