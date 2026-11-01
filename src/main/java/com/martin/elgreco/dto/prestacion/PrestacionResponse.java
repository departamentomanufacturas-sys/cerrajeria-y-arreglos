package com.martin.elgreco.dto.prestacion;

import com.martin.elgreco.domain.CategoriaPrestacion;

import java.time.LocalDateTime;

public record PrestacionResponse(
        Long id,
        String nombre,
        CategoriaPrestacion categoria,
        String resumen,
        String contenido,
        String slug,
        String seoTitle,
        String seoDescription,
        String imagenPrincipal,
        Boolean destacada,
        Boolean activa,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
