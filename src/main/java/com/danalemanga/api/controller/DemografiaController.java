package com.danalemanga.api.controller;

import com.danalemanga.api.interfaceService.IDemografiaService;
import com.danalemanga.api.model.Demografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/demografias")
public class DemografiaController {

    @Autowired
    private IDemografiaService service;

    @GetMapping("/{nombre_demo}")
    public ResponseEntity<?> obtenerDemografia(@PathVariable String nombre_demo) {
        Demografia demo = null;
        Map<String, Object> response = new HashMap<>();
        try {
            demo = service.findByNombre(nombre_demo);
            if (demo != null) {
                return new ResponseEntity<>(demo, HttpStatus.OK);
            } else {
                response.put("mensaje", "La demografía no fue encontrada.");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
