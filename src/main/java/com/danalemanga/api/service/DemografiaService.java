package com.danalemanga.api.service;

import com.danalemanga.api.interfaceService.IDemografiaService;
import com.danalemanga.api.model.Demografia;
import com.danalemanga.api.repository.DemografiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemografiaService implements IDemografiaService {

    @Autowired
    private DemografiaRepository repository;

    @Override
    public Demografia findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
}
