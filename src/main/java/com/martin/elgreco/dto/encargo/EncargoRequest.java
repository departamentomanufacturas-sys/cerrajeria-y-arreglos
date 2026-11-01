package com.martin.elgreco.dto.encargo;

import com.martin.elgreco.domain.EstadoEncargo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record EncargoRequest(
        @NotNull EstadoEncargo estado,
        @PositiveOrZero BigDecimal presupuesto) {
}
