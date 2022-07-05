package com.danalemanga.api.service;

import com.danalemanga.api.interfaceService.IMangaService;
import com.danalemanga.api.model.Manga;
import com.danalemanga.api.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MangaService implements IMangaService {

    @Autowired
    private MangaRepository repository;

    @Override
    public Page<Manga> listarMangas(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Manga guardarManga(Manga m) {
        return repository.save(m);
    }

    @Override
    public Manga buscarManga(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Manga findByUrl(String url) {
        return repository.findByUrl(url);
    }
}
