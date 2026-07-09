package com.manuel.safehub_api.domain.client;

import com.manuel.safehub_api.controller.dto.client.ClientFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientRepository {

    Client save(Client client);

    Page<Client> search(Long organizationId, ClientFilter criteria, Pageable pageable);
}
