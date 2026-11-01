package com.martin.elgreco.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String autor;

    @Column(nullable = false)
    private Integer estrellas;

    @Column(nullable = false, length = 2000)
    private String comentario;

    @Column(nullable = false)
    private Boolean destacada = false;

    @Column(nullable = false)
    private LocalDate fechaReview;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prestacion_id")
    private Prestacion prestacion;
}
