package com.martin.elgreco.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "encargo")
@Getter
@Setter
public class Encargo {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String nombreCliente;

    @Setter
    @Getter
    @Column(nullable = false)
    private String titulo;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaPrestacion categoria;

    @Setter
    @Column(length = 2000)
    private String descripcion;

    @Setter
    @Getter
    @Column(nullable = false)
    private String telefono;

    @Setter
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal presupuesto;

    @Getter
    @ElementCollection
    @CollectionTable(name = "encargo_imagenes", joinColumns = @JoinColumn(name = "encargo_id"))
    @Column(name = "imagen", nullable = false)
    private List<String> imagenes = new ArrayList<>();

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEncargo estado;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prestacion_id")
    private Prestacion prestacion;

    @Setter
    @Getter
    @Column(nullable = false)
    private LocalDateTime fechaSolicitud;

    @Setter
    @Getter
    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;


    protected Encargo() {
    }

    public Encargo(
            String nombreCliente,
            String titulo,
            CategoriaPrestacion categoria,
            String descripcion,
            String telefono,
            BigDecimal presupuesto,
            List<String> imagenes,
            EstadoEncargo estado) {
        this.nombreCliente = nombreCliente;
        this.titulo = titulo;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.presupuesto = presupuesto;
        this.imagenes = imagenes == null ? new ArrayList<>() : new ArrayList<>(imagenes);
        this.estado = estado;
    }

    @PrePersist
    void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        if (fechaSolicitud == null) {
            fechaSolicitud = now;
        }
        fechaActualizacion = now;
    }

    @PreUpdate
    void preUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    public CategoriaPrestacion getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPresupuesto() {
        return presupuesto;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes == null ? new ArrayList<>() : new ArrayList<>(imagenes);
    }

    public EstadoEncargo getEstado() {
        return estado;
    }


}
