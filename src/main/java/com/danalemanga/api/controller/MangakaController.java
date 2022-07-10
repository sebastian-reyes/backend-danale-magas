package com.danalemanga.api.controller;

import com.danalemanga.api.interfaceService.IMangakaService;
import com.danalemanga.api.model.Mangaka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mangakas")
public class MangakaController {

    @Autowired
    private IMangakaService service;

    @GetMapping
    public ResponseEntity<?> listarMangakas() {
        Map<String, Object> response = new HashMap<>();
        List<Mangaka> mangakas = service.listarMangakas();
        response.put("mangakas", mangakas);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarMangaka(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        Mangaka mangaka = null;
        try {
            mangaka = service.buscarMangaka(id);
            if (mangaka != null) {
                return new ResponseEntity<>(mangaka, HttpStatus.OK);
            } else {
                response.put("mensaje", "No se encontró este mangaka en la base de datos.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
