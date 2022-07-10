package com.danalemanga.api.service;

import com.danalemanga.api.interfaceService.IMangakaService;
import com.danalemanga.api.model.Mangaka;
import com.danalemanga.api.repository.MangakaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangakaService implements IMangakaService {

    @Autowired
    private MangakaRepository repository;

    @Override
    public List<Mangaka> listarMangakas() {
        return repository.findAll();
    }

    @Override
    public Mangaka buscarMangaka(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Mangaka guardarMangaka(Mangaka m) {
        return repository.save(m);
    }
}
