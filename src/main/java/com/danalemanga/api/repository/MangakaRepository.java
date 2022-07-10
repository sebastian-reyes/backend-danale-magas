package com.danalemanga.api.repository;

import com.danalemanga.api.model.Mangaka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangakaRepository extends JpaRepository<Mangaka, Integer> {
}
