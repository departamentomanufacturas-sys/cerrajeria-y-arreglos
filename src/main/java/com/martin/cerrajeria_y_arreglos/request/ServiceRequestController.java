package com.martin.cerrajeria_y_arreglos.request;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/requests")
public class ServiceRequestController {

    private final ServiceRequestService service;

    public ServiceRequestController(ServiceRequestService service) {
        this.service = service;
    }

    @GetMapping
    public String findAll() {
        return "Holaaaa a todos";
                //service.findAll();
    }

    @GetMapping("/{id}")
    public ServiceRequest findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceRequest create(@Valid @RequestBody ServiceRequestInput input) {
        return service.create(input);
    }

    @PutMapping("/{id}")
    public ServiceRequest update(@PathVariable Long id, @Valid @RequestBody ServiceRequestInput input) {
        return service.update(id, input);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
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