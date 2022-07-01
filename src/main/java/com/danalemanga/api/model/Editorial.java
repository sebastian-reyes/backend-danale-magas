package com.danalemanga.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "editoriales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Editorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_editorial;

    @Column(length = 100, unique = true, nullable = false)
    private String nombre_editorial;

    @Column(length = 60, nullable = false)
    private String pais;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
}
