package com.manuel.safehub_api.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OrganizationRequest {
    private String name;
    private String email;

    @NotBlank
    @Size(max = 255)
    public String getName() {
        return name;
    }

    @NotBlank
    @Email(message = "Invalid email format") // il messaggio non va deifinito per forza, esiste un messaggio di default.
    public String getEmail() {
        return email;
    }
}
