package com.martin.cerrajeria_y_arreglos.servicio;

import com.martin.cerrajeria_y_arreglos.domain.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicioRepository extends JpaRepository<Servicio, UUID> {
}
