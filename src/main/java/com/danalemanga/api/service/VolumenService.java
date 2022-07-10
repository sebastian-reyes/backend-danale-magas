package com.danalemanga.api.service;

import com.danalemanga.api.interfaceService.IVolumenService;
import com.danalemanga.api.model.Volumen;
import com.danalemanga.api.repository.VolumenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolumenService implements IVolumenService {

    @Autowired
    private VolumenRepository repository;

    @Override
    public Volumen buscarVolumen(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Volumen guardarVolumen(Volumen v) {
        return repository.save(v);
    }

    @Override
    public Volumen findByUrl(String url) {
        return repository.findByUrl(url);
    }
}
