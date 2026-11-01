package com.martin.elgreco.publicapi;

import com.martin.elgreco.dto.contacto.ContactoRequest;
import com.martin.elgreco.dto.contacto.ContactoResponse;
import com.martin.elgreco.service.ContactoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/contacto")
public class ContactoController {

    private final ContactoService service;

    public ContactoController(ContactoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactoResponse create(@Valid @RequestBody ContactoRequest request) {
        return service.create(request);
    }
}
