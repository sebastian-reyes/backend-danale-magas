package com.danalemanga.api.interfaceService;

import com.danalemanga.api.model.Volumen;

public interface IVolumenService {
    public Volumen buscarVolumen(int id);
    public Volumen guardarVolumen(Volumen v);
    public Volumen findByUrl(String url);
}
