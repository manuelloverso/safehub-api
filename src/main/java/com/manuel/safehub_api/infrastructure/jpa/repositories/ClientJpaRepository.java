package com.manuel.safehub_api.infrastructure.jpa.repositories;

import com.manuel.safehub_api.infrastructure.jpa.entities.ClientJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClientJpaRepository extends JpaRepository<ClientJpaEntity, Long>,
                                             JpaSpecificationExecutor<ClientJpaEntity> {
}
