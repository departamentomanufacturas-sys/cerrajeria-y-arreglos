package com.martin.elgreco.admin;

import com.martin.elgreco.dto.contacto.ContactoResponse;
import com.martin.elgreco.service.ContactoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/contactos")
public class ContactoAdminController {

    private final ContactoService service;

    public ContactoAdminController(ContactoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ContactoResponse> findAll() {
        return service.findAll();
    }
}
