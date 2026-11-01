package com.martin.elgreco.repository;

import com.martin.elgreco.domain.Prestacion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestacionRepository extends JpaRepository<Prestacion, Long> {
    List<Prestacion> findAllByActivaTrue();

    Optional<Prestacion> findBySlugAndActivaTrue(String slug);

    boolean existsBySlug(String slug);
}
