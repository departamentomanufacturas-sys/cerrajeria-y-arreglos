package com.martin.elgreco.admin;

import com.martin.elgreco.dto.prestacion.PrestacionRequest;
import com.martin.elgreco.dto.prestacion.PrestacionResponse;
import com.martin.elgreco.service.PrestacionService;
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
@RequestMapping("/api/admin/prestaciones")
public class PrestacionAdminController {

    private final PrestacionService service;

    public PrestacionAdminController(PrestacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<PrestacionResponse> findAll() {
        return service.findAllAdmin();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrestacionResponse create(@Valid @RequestBody PrestacionRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public PrestacionResponse update(@PathVariable Long id, @Valid @RequestBody PrestacionRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
