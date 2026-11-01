package com.martin.elgreco.dto.review;

import java.time.LocalDate;

public record ReviewResponse(
        Long id,
        String autor,
        Integer estrellas,
        String comentario,
        Boolean destacada,
        LocalDate fechaReview,
        Long prestacionId,
        String prestacionNombre,
        String prestacionSlug) {
}
