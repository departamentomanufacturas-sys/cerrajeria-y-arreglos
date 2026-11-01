package com.martin.elgreco.publicapi;

import com.martin.elgreco.dto.prestacion.PrestacionResponse;
import com.martin.elgreco.service.PrestacionService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/prestaciones")
public class PrestacionController {

    private final PrestacionService service;

    public PrestacionController(PrestacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<PrestacionResponse> findAll() {
        return service.findPublic();
    }

    @GetMapping("/{slug}")
    public PrestacionResponse findBySlug(@PathVariable String slug) {
        return service.findPublicBySlug(slug);
    }
}
