package com.martin.elgreco.dto.encargo;

import com.martin.elgreco.domain.CategoriaPrestacion;
import com.martin.elgreco.domain.EstadoEncargo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record EncargoResponse(
        Long id,
        String nombreCliente,
        String titulo,
        CategoriaPrestacion categoria,
        String descripcion,
        String telefono,
        BigDecimal presupuesto,
        List<String> imagenes,
        EstadoEncargo estado,
        Long prestacionId,
        String prestacionNombre,
        LocalDateTime fechaSolicitud,
        LocalDateTime fechaActualizacion) {
}
