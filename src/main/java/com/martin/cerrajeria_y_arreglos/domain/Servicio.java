package com.martin.cerrajeria_y_arreglos.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Getter
    @Column(nullable = false)
    private String nombre;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaServicio categoria;

    @Setter
    @Column(length = 2000)
    private String descripcion;

    @Setter
    @Getter
    @Column(nullable = false)
    private Integer telefono;

    @Setter
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Getter
    @ElementCollection
    @CollectionTable(name = "servicio_imagenes", joinColumns = @JoinColumn(name = "servicio_id"))
    @Column(name = "imagen", nullable = false)
    private List<String> imagenes = new ArrayList<>();

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoServicio estado;

    protected Servicio() {
    }

    public Servicio(
            String nombre,
            CategoriaServicio categoria,
            String descripcion,
            Integer telefono,
            BigDecimal precio,
            List<String> imagenes,
            EstadoServicio estado) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.telefono= telefono;
        this.precio = precio;
        this.imagenes = imagenes == null ? new ArrayList<>() : new ArrayList<>(imagenes);
        this.estado = estado;
    }

    public CategoriaServicio getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes == null ? new ArrayList<>() : new ArrayList<>(imagenes);
    }

    public EstadoServicio getEstado() {
        return estado;
    }

}
