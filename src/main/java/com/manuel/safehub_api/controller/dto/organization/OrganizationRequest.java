package com.manuel.safehub_api.controller.dto.organization;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OrganizationRequest {
    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Email(message = "Invalid email format")
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
