package com.martin.elgreco.dto.contacto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ContactoRequest(
        @NotBlank @Size(max = 120) String nombre,
        @NotBlank @Size(max = 40) String telefono,
        @Email @Size(max = 254) String email,
        @NotBlank @Size(max = 4000) String mensaje) {
}
