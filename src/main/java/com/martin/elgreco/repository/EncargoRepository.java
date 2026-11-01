package com.martin.elgreco.repository;

import com.martin.elgreco.domain.Encargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncargoRepository extends JpaRepository<Encargo, Long> {
}
