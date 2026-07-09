package com.manuel.safehub_api.controller;

import com.manuel.safehub_api.application.services.client.ClientService;
import com.manuel.safehub_api.controller.dto.client.ClientFilter;
import com.manuel.safehub_api.controller.dto.client.ClientRequest;
import com.manuel.safehub_api.controller.dto.client.ClientResponse;
import com.manuel.safehub_api.controller.dto.common.PagedResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody @Valid ClientRequest request) {
        ClientResponse response = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<ClientResponse>> getList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String vatNumber,
            Pageable pageable) {

        ClientFilter clientFilter = new ClientFilter(name, vatNumber);
        return ResponseEntity.ok(clientService.getList(clientFilter, pageable));
    }
}
