package com.danalemanga.api.repository;

import com.danalemanga.api.model.Volumen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolumenRepository extends JpaRepository<Volumen, Integer> {
    public Volumen findByUrl(String url);
}
