package com.danalemanga.api.interfaceService;

import com.danalemanga.api.model.Mangaka;

import java.util.List;

public interface IMangakaService {
    public List<Mangaka> listarMangakas();
    public Mangaka buscarMangaka(int id);
    public Mangaka guardarMangaka(Mangaka m);
}
