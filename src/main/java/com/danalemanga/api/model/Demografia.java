package com.danalemanga.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "demografias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demografia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_demografia;

    @Column(length = 60,unique = true, name = "nombre_demografia")
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "demografia", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer","hibernateLazyInitializer","demografia","volumenes"})
    private List<Manga> mangas;

    /**
     *
     */
    private static final long serialVersionUID = 1L;
}
