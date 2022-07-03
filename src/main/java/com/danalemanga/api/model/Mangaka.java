package com.danalemanga.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "mangakas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mangaka implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_mangaka;

    @Column(length = 70, unique = true)
    private String nombre_mangaka;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mangaka", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","mangaka","id_manga","editorial","volumenes"})
    private List<Manga> mangas;

    /**
    *
    */
    private static final long serialVersionUID = 1L;
}
