package com.danalemanga.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "volumenes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Volumen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_volumen;

    @Column(length = 85)
    private String nombre_volumen;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer n_tomo;

    @Column(length = 13)
    private String ISBN;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock_act;

    @Column(nullable = false)
    private Integer stock_min;

    @Column(nullable = false)
    private String foto;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_manga")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "volumenes", "id_manga"})
    private Manga manga;

    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
