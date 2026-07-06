package com.manuel.safehub_api.controller;

import com.manuel.safehub_api.application.services.client.ClientService;
import com.manuel.safehub_api.controller.dto.client.ClientRequest;
import com.manuel.safehub_api.controller.dto.client.ClientResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/api/v1/clients")
    public ClientResponse createClient(@RequestBody @Valid ClientRequest request) {
        return clientService.createClient(request);
    }
}
