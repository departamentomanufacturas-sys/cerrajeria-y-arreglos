package com.martin.cerrajeria_y_arreglos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServiceRequestInput(
        @NotBlank String customerName,
        @NotBlank String phone,
        @NotNull ServiceType serviceType,
        @NotBlank String description) {
}
