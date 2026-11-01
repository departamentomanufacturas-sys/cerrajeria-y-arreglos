package com.martin.elgreco.service;

import com.martin.elgreco.domain.Encargo;
import com.martin.elgreco.dto.encargo.EncargoRequest;
import com.martin.elgreco.dto.encargo.EncargoResponse;
import com.martin.elgreco.exception.ResourceNotFoundException;
import com.martin.elgreco.repository.EncargoRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EncargoService {

    private final EncargoRepository repository;

    public EncargoService(EncargoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<EncargoResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public EncargoResponse update(Long id, EncargoRequest request) {
        Encargo encargo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Encargo no encontrado: " + id));
        encargo.setEstado(request.estado());
        if (request.presupuesto() != null) {
            encargo.setPresupuesto(request.presupuesto());
        }
        return toResponse(repository.save(encargo));
    }

    private EncargoResponse toResponse(Encargo encargo) {
        return new EncargoResponse(
                encargo.getId(),
                encargo.getNombreCliente(),
                encargo.getTitulo(),
                encargo.getCategoria(),
                encargo.getDescripcion(),
                encargo.getTelefono(),
                encargo.getPresupuesto(),
                List.copyOf(encargo.getImagenes()),
                encargo.getEstado(),
                encargo.getPrestacion() == null ? null : encargo.getPrestacion().getId(),
                encargo.getPrestacion() == null ? null : encargo.getPrestacion().getNombre(),
                encargo.getFechaSolicitud(),
                encargo.getFechaActualizacion());
    }
}
