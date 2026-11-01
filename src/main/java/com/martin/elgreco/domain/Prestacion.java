package com.martin.elgreco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prestaciones")
@Getter
@Setter
@NoArgsConstructor
public class Prestacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private CategoriaPrestacion categoria;

    @Column(length = 500)
    private String resumen;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @Column(nullable = false, unique = true, length = 180)
    private String slug;

    @Column(length = 180)
    private String seoTitle;

    @Column(length = 500)
    private String seoDescription;

    @Column(length = 1000)
    private String imagenPrincipal;

    @Column(nullable = false)
    private Boolean destacada = false;

    @Column(nullable = false)
    private Boolean activa = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
        if (destacada == null) destacada = false;
        if (activa == null) activa = true;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
