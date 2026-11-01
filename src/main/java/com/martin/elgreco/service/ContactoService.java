package com.martin.elgreco.service;

import com.martin.elgreco.domain.Contacto;
import com.martin.elgreco.dto.contacto.ContactoRequest;
import com.martin.elgreco.dto.contacto.ContactoResponse;
import com.martin.elgreco.repository.ContactoRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContactoService {

    private final ContactoRepository repository;

    public ContactoService(ContactoRepository repository) {
        this.repository = repository;
    }

    public ContactoResponse create(ContactoRequest request) {
        Contacto contacto = new Contacto();
        contacto.setNombre(request.nombre());
        contacto.setTelefono(request.telefono());
        contacto.setEmail(request.email());
        contacto.setMensaje(request.mensaje());
        return toResponse(repository.save(contacto));
    }

    @Transactional(readOnly = true)
    public List<ContactoResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    private ContactoResponse toResponse(Contacto contacto) {
        return new ContactoResponse(
                contacto.getId(),
                contacto.getNombre(),
                contacto.getTelefono(),
                contacto.getEmail(),
                contacto.getMensaje(),
                contacto.getLeido(),
                contacto.getFechaContacto());
    }
}
