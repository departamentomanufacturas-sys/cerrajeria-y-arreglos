package com.martin.elgreco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contactos")
@Getter
@Setter
@NoArgsConstructor
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, length = 40)
    private String telefono;

    @Column(length = 254)
    private String email;

    @Column(nullable = false, length = 4000)
    private String mensaje;

    @Column(nullable = false)
    private Boolean leido = false;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaContacto;

    @PrePersist
    void prePersist() {
        if (fechaContacto == null) {
            fechaContacto = LocalDateTime.now();
        }
        if (leido == null) {
            leido = false;
        }
    }
}
