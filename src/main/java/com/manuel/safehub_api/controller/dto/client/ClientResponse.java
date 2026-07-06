package com.manuel.safehub_api.controller.dto.client;

public record ClientResponse(
    Long id,
    String name,
    String legalName,
    String vatNumber,
    String email,
    String phone,
    String notes
) {}
