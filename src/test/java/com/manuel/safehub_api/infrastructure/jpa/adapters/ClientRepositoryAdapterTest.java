package com.manuel.safehub_api.infrastructure.jpa.adapters;

import com.manuel.safehub_api.domain.client.Client;
import com.manuel.safehub_api.infrastructure.jpa.entities.ClientJpaEntity;
import com.manuel.safehub_api.infrastructure.jpa.repositories.ClientJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientRepositoryAdapterTest {

    @Mock
    private ClientJpaRepository clientJpaRepository;

    @InjectMocks
    private ClientRepositoryAdapter adapter;

    private static Client newClient() {
        return Client.create(1L, "ACME", "ACME S.r.l.", "IT123", "a@b.it", null, null);
    }

    @Test
    void save_returnsDomainClient_onSuccess() {
        ClientJpaEntity persisted = new ClientJpaEntity();
        persisted.setId(42L);
        persisted.setOrganizationId(1L);
        persisted.setName("ACME");
        persisted.setLegalName("ACME S.r.l.");
        persisted.setVatNumber("IT123");
        persisted.setEmail("a@b.it");
        persisted.setStatus("active");
        when(clientJpaRepository.save(any(ClientJpaEntity.class))).thenReturn(persisted);

        Client result = adapter.save(newClient());

        assertThat(result.getId()).isEqualTo(42L);
        assertThat(result.getName()).isEqualTo("ACME");
    }
}
