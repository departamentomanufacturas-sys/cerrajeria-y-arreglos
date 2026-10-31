package com.martin.cerrajeria_y_arreglos.servicio;

import com.martin.cerrajeria_y_arreglos.domain.Servicio;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private static final Logger logger = LoggerFactory.getLogger(ServicioController.class);

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public List<Servicio> findAll() {
        logger.info("Solicitud HTTP para consultar todos los servicios");
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public Servicio findById(@PathVariable UUID id) {
        logger.info("Solicitud HTTP para consultar el servicio con id={}", id);
        return servicioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servicio create(@Valid @RequestBody ServicioRequest input) {
        logger.info("Solicitud HTTP para crear un servicio");
        return servicioService.create(input);
    }

    @PutMapping("/{id}")
    public Servicio update(@PathVariable UUID id, @Valid @RequestBody ServicioRequest input) {
        logger.info("Solicitud HTTP para actualizar el servicio con id={}", id);
        return servicioService.update(id, input);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        logger.info("Solicitud HTTP para eliminar el servicio con id={}", id);
        servicioService.delete(id);
    }
}


/*ESTRUCTURA GLOBAL PROPUESTA
*
*
*
*
* backend/
└── src/main/java/com/mitienda/
    ├── MiTiendaApplication.java
    ├── config/
    │   └── CorsConfig.java
    ├── controller/
    │   ├── ServicioController.java
    │   ├── ContactoController.java
    │   └── PresupuestoController.java
    ├── service/
    │   ├── ServicioService.java
    │   ├── ContactoService.java
    │   └── PresupuestoService.java
    ├── repository/
    │   ├── ServicioRepository.java
    │   └── PresupuestoRepository.java
    ├── entity/
    │   ├── Servicio.java
    │   └── SolicitudPresupuesto.java
    ├── dto/
    │   ├── ServicioResponse.java
    │   └── PresupuestoRequest.java
    └── exception/
        └── GlobalExceptionHandler.java
* */

/*CONTROLLER PROPUESTO
*
*
*
* @RestController
@RequestMapping("/api/servicios")
class ServicioController {

    private final ServicioService servicioService;

    GET "/":
        obtenerServicios(categoriaOpcional):
            servicios = servicioService.buscarActivos(categoriaOpcional)
            devolver HTTP 200 con servicios

    GET "/{id}":
        obtenerServicio(id):
            servicio = servicioService.buscarPorId(id)

            si servicio no existe:
                devolver HTTP 404

            devolver HTTP 200 con servicio
}*/