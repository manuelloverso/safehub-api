package com.manuel.safehub_api.infrastructure.jpa.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "organizations")
public class OrganizationJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    //non servirebbe specificare il nome perchè jpa converte in automatico da camelCase a snake_case
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //non servirebbe specificare il nome perchè jpa converte in automatico da camelCase a snake_case
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public OrganizationJpaEntity() {}

    public OrganizationJpaEntity(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
