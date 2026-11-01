package com.martin.elgreco.dto.contacto;

import java.time.LocalDateTime;

public record ContactoResponse(
        Long id,
        String nombre,
        String telefono,
        String email,
        String mensaje,
        Boolean leido,
        LocalDateTime fechaContacto) {
}
