package com.manuel.safehub_api.infrastructure.jpa.repositories;

import com.manuel.safehub_api.infrastructure.jpa.entities.OrganizationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationJpaRepository extends JpaRepository<OrganizationJpaEntity, Long> {

}
