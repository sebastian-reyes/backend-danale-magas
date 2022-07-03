package com.danalemanga.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "mangas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manga implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_manga;

    @Column(length = 100, nullable = false)
    private String nombre_manga;

    @Column(columnDefinition = "TEXT")
    private String sinopsis;

    @Column(unique = true)
    private String foto;

    @Column(unique = true, name = "url_manga")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mangaka")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "mangas", "id_mangaka"})
    private Mangaka mangaka;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demografia")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "mangas", "id_demografia", "descripcion"})
    private Demografia demografia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_editorial")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","mangas","id_editorial"})
    private Editorial editorial;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manga", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","manga"})
    private List<Volumen> volumenes;

    public Integer getNumeroVolumenes(){
        return volumenes.size();
    }

    /**
    *
    */
    private static final long serialVersionUID = 1L;
}
