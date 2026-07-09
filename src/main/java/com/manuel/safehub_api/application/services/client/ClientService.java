package com.manuel.safehub_api.application.services.client;

import com.manuel.safehub_api.controller.dto.client.ClientFilter;
import com.manuel.safehub_api.controller.dto.client.ClientRequest;
import com.manuel.safehub_api.controller.dto.client.ClientResponse;
import com.manuel.safehub_api.controller.dto.common.PagedResponse;
import com.manuel.safehub_api.domain.client.Client;
import com.manuel.safehub_api.domain.client.ClientRepository;
import org.springframework.data.domain.Pageable;
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

    @Transactional(readOnly = true)
    public PagedResponse<ClientResponse> getList(ClientFilter clientFilter, Pageable pageable) {
        // TODO(auth): organizationId must come from authentication — same seam as createClient
        Long organizationId = 1L;


        return PagedResponse.from(
            clientRepository.search(organizationId, clientFilter, pageable)
                .map(this::toResponse)
        );
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
