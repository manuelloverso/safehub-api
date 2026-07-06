package com.manuel.safehub_api.infrastructure.jpa.repositories;

import com.manuel.safehub_api.infrastructure.jpa.entities.ClientJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientJpaRepository extends JpaRepository<ClientJpaEntity, Long> {
}
