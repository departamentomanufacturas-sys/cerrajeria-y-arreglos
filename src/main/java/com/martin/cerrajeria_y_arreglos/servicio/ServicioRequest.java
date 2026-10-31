package com.martin.cerrajeria_y_arreglos.servicio;

import com.martin.cerrajeria_y_arreglos.domain.CategoriaServicio;
import com.martin.cerrajeria_y_arreglos.domain.EstadoServicio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record ServicioRequest(
        @NotBlank String nombre,
        @NotNull CategoriaServicio categoria,
        String descripcion,
        @NotNull @Positive Integer telefono,
        @NotNull @Positive BigDecimal precio,
        List<String> imagenes,
        @NotNull EstadoServicio estado) {
}
