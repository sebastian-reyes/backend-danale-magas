package com.danalemanga.api.repository;

import com.danalemanga.api.model.Demografia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemografiaRepository extends JpaRepository<Demografia, Integer> {
    public Demografia findByNombre(String nombre);
}
