package com.martin.cerrajeria_y_arreglos.servicio;

import com.martin.cerrajeria_y_arreglos.domain.Servicio;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServicioService {

    private static final Logger logger = LoggerFactory.getLogger(ServicioService.class);

    private final ServicioRepository repository;

    public ServicioService(ServicioRepository repository) {
        this.repository = repository;
    }

    public List<Servicio> findAll() {
        logger.debug("Consultando todos los servicios en el repositorio");
        List<Servicio> servicios = repository.findAll();
        logger.info("Consulta de servicios completada: {} resultado(s)", servicios.size());
        return servicios;
    }

    public Servicio findById(UUID id) {
        logger.debug("Buscando servicio con id={} en el repositorio", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("No se encontró el servicio con id={}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado");
                });
    }

    public Servicio create(ServicioRequest input) {
        logger.debug("Preparando el servicio para guardarlo en el repositorio");
        Servicio servicioGuardado = repository.save(toServicio(input));
        logger.info("Servicio creado correctamente con id={}", servicioGuardado.getId());
        return servicioGuardado;
    }

    public Servicio update(UUID id, ServicioRequest input) {
        logger.debug("Actualizando los datos del servicio con id={}", id);
        Servicio servicio = findById(id);
        servicio.setNombre(input.nombre());
        servicio.setCategoria(input.categoria());
        servicio.setDescripcion(input.descripcion());
        servicio.setTelefono(input.telefono());
        servicio.setPrecio(input.precio());
        servicio.setImagenes(input.imagenes());
        servicio.setEstado(input.estado());
        Servicio servicioActualizado = repository.save(servicio);
        logger.info("Servicio actualizado correctamente con id={}", servicioActualizado.getId());
        return servicioActualizado;
    }

    public void delete(UUID id) {
        logger.debug("Eliminando el servicio con id={}", id);
        repository.delete(findById(id));
        logger.info("Servicio eliminado correctamente con id={}", id);
    }

    private Servicio toServicio(ServicioRequest input) {
        return new Servicio(
                input.nombre(),
                input.categoria(),
                input.descripcion(),
                input.telefono(),
                input.precio(),
                input.imagenes(),
                input.estado());
    }
}
