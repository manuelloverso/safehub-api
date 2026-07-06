package com.manuel.safehub_api.infrastructure.jpa.adapters;

import com.manuel.safehub_api.domain.client.Client;
import com.manuel.safehub_api.domain.client.ClientRepository;
import com.manuel.safehub_api.domain.client.ClientStatus;
import com.manuel.safehub_api.infrastructure.jpa.entities.ClientJpaEntity;
import com.manuel.safehub_api.infrastructure.jpa.repositories.ClientJpaRepository;
import org.springframework.stereotype.Component;


@Component
public class ClientRepositoryAdapter implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;

    public ClientRepositoryAdapter(ClientJpaRepository clientJpaRepository) {
        this.clientJpaRepository = clientJpaRepository;
    }

    @Override
    public Client save(Client client) {
        ClientJpaEntity entity = toJpa(client);
        ClientJpaEntity saved = clientJpaRepository.save(entity);
        return toDomain(saved);
    }

    private ClientJpaEntity toJpa(Client client) {
        ClientJpaEntity entity = new ClientJpaEntity();
        entity.setId(client.getId());                 // null for a new client → DB generates it
        entity.setOrganizationId(client.getOrganizationId());
        entity.setName(client.getName());
        entity.setLegalName(client.getLegalName());
        entity.setVatNumber(client.getVatNumber());
        entity.setEmail(client.getEmail());
        entity.setPhone(client.getPhone());
        entity.setNotes(client.getNotes());
        entity.setStatus(client.getStatus().dbValue());
        return entity;
    }

    private Client toDomain(ClientJpaEntity entity) {
        return Client.reconstitute(
            entity.getId(),
            entity.getOrganizationId(),
            entity.getName(),
            entity.getLegalName(),
            entity.getVatNumber(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getNotes(),
            ClientStatus.fromDbValue(entity.getStatus())
        );
    }
}
