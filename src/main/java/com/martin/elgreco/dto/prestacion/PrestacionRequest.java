package com.martin.elgreco.dto.prestacion;

import com.martin.elgreco.domain.CategoriaPrestacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PrestacionRequest(
        @NotBlank @Size(max = 150) String nombre,
        @NotNull CategoriaPrestacion categoria,
        @Size(max = 500) String resumen,
        String contenido,
        @NotBlank @Size(max = 180) @Pattern(regexp = "[a-z0-9]+(?:-[a-z0-9]+)*") String slug,
        @Size(max = 180) String seoTitle,
        @Size(max = 500) String seoDescription,
        @Size(max = 1000) String imagenPrincipal,
        Boolean destacada,
        Boolean activa) {
}
