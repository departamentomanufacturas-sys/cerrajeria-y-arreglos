package com.martin.elgreco.admin;

import com.martin.elgreco.dto.encargo.EncargoRequest;
import com.martin.elgreco.dto.encargo.EncargoResponse;
import com.martin.elgreco.service.EncargoService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/encargos")
public class EncargoAdminController {

    private final EncargoService service;

    public EncargoAdminController(EncargoService service) {
        this.service = service;
    }

    @GetMapping
    public List<EncargoResponse> findAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public EncargoResponse update(@PathVariable Long id, @Valid @RequestBody EncargoRequest request) {
        return service.update(id, request);
    }
}
