package com.app.service;


import com.app.persistence.EstadoEntity;
import com.app.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class EstadoService {
    private final EstadoRepository estadoRepository;

    @Autowired

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }


    public Optional<EstadoEntity> findByNombre(String nombre) {
        log.info("Buscando estado con nombre: {}", nombre);
        return estadoRepository.findByNombre(nombre);
    }
}