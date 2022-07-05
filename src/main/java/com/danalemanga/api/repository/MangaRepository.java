package com.danalemanga.api.repository;

import com.danalemanga.api.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaRepository extends JpaRepository<Manga, Integer> {
    public Manga findByUrl(String url);
}
