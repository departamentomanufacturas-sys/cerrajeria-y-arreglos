package com.martin.elgreco.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ReviewRequest(
        @NotBlank @Size(max = 120) String autor,
        @NotNull @Min(1) @Max(5) Integer estrellas,
        @NotBlank @Size(max = 2000) String comentario,
        Boolean destacada,
        @PastOrPresent LocalDate fechaReview,
        Long prestacionId) {
}
