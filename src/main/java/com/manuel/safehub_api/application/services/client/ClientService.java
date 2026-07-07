package com.manuel.safehub_api.application.services.client;

import com.manuel.safehub_api.controller.dto.client.ClientRequest;
import com.manuel.safehub_api.controller.dto.client.ClientResponse;
import com.manuel.safehub_api.domain.client.Client;
import com.manuel.safehub_api.domain.client.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientResponse createClient(ClientRequest request) {
        // TODO(auth): organizationId must come from authentication
        Long organizationId = 1L;

        Client client = Client.create(
            organizationId,
            request.getName(),
            request.getLegalName(),
            request.getVatNumber(),
            request.getEmail(),
            request.getPhone(),
            request.getNotes()
        );

        Client savedClient = clientRepository.save(client);
        return toResponse(savedClient);
    }

    private ClientResponse toResponse(Client client) {
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
