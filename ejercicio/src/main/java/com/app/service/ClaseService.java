package com.app.service;

import com.app.persistence.ClaseEntity;
import com.app.repository.ClaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClaseService {
    private final ClaseRepository claseRepository;


    @Autowired
    public ClaseService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }
    public ClaseEntity save(ClaseEntity clase) {
        return claseRepository.save(clase);
    }

    public boolean isDocenteOfClase(String username, Long claseId) {
        ClaseEntity clase = claseRepository.findById(claseId)
                .orElse(null);
        if (clase == null) return false;
        return clase.getDocente().equals(username);
    }

    public List<ClaseEntity> findClasesByDocente(String username) {
        return claseRepository.findByDocente(username);
    }
}