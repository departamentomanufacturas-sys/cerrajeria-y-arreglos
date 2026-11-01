package com.martin.elgreco.repository;

import com.martin.elgreco.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {
}
