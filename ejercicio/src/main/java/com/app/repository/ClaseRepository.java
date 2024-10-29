package com.app.repository;

import com.app.persistence.ClaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClaseRepository extends JpaRepository<ClaseEntity, Long> {
    List<ClaseEntity> findByDocente(String docente);
}
