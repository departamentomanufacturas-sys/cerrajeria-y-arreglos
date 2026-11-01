package com.martin.elgreco.service;

import com.martin.elgreco.domain.Prestacion;
import com.martin.elgreco.dto.prestacion.PrestacionRequest;
import com.martin.elgreco.dto.prestacion.PrestacionResponse;
import com.martin.elgreco.exception.BusinessException;
import com.martin.elgreco.exception.ResourceNotFoundException;
import com.martin.elgreco.repository.PrestacionRepository;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrestacionService {

    private final PrestacionRepository repository;

    public PrestacionService(PrestacionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<PrestacionResponse> findPublic() {
        return repository.findAllByActivaTrue().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PrestacionResponse findPublicBySlug(String slug) {
        Prestacion prestacion = repository.findBySlugAndActivaTrue(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Prestación no encontrada: " + slug));
        return toResponse(prestacion);
    }

    @Transactional(readOnly = true)
    public List<PrestacionResponse> findAllAdmin() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public PrestacionResponse create(PrestacionRequest request) {
        if (repository.existsBySlug(request.slug())) {
            throw new BusinessException("Ya existe una prestación con ese slug");
        }
        Prestacion prestacion = new Prestacion();
        apply(prestacion, request);
        return toResponse(repository.save(prestacion));
    }

    public PrestacionResponse update(Long id, PrestacionRequest request) {
        Prestacion prestacion = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestación no encontrada: " + id));
        if (!prestacion.getSlug().equals(request.slug()) && repository.existsBySlug(request.slug())) {
            throw new BusinessException("Ya existe una prestación con ese slug");
        }
        apply(prestacion, request);
        return toResponse(repository.save(prestacion));
    }

    public void delete(Long id) {
        Prestacion prestacion = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestación no encontrada: " + id));
        prestacion.setActiva(false);
        repository.save(prestacion);
    }

    private void apply(Prestacion prestacion, PrestacionRequest request) {
        prestacion.setNombre(request.nombre());
        prestacion.setCategoria(request.categoria());
        prestacion.setResumen(request.resumen());
        prestacion.setContenido(request.contenido());
        prestacion.setSlug(request.slug());
        prestacion.setSeoTitle(request.seoTitle());
        prestacion.setSeoDescription(request.seoDescription());
        prestacion.setImagenPrincipal(request.imagenPrincipal());
        prestacion.setDestacada(request.destacada() != null && request.destacada());
        prestacion.setActiva(request.activa() == null || request.activa());
    }

    private PrestacionResponse toResponse(Prestacion prestacion) {
        return new PrestacionResponse(
                prestacion.getId(),
                prestacion.getNombre(),
                prestacion.getCategoria(),
                prestacion.getResumen(),
                prestacion.getContenido(),
                prestacion.getSlug(),
                prestacion.getSeoTitle(),
                prestacion.getSeoDescription(),
                prestacion.getImagenPrincipal(),
                prestacion.getDestacada(),
                prestacion.getActiva(),
                prestacion.getCreatedAt(),
                prestacion.getUpdatedAt());
    }
}
