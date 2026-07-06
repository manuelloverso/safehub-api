package com.manuel.safehub_api.application.services.client;

import com.manuel.safehub_api.controller.dto.client.ClientResponse;
import com.manuel.safehub_api.domain.client.Client;
import org.springframework.stereotype.Component;


@Component
public class ClientDtoAssembler {

    public ClientResponse toResponse(Client client) {
        return new ClientResponse(
            client.getId(),
            client.getName(),
            client.getLegalName(),
            client.getVatNumber(),
            client.getEmail(),
            client.getPhone(),
            client.getNotes()
        );
    }
}
