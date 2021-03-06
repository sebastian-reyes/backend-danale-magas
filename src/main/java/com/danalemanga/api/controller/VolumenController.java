package com.danalemanga.api.controller;

import com.danalemanga.api.interfaceService.IVolumenService;
import com.danalemanga.api.model.Volumen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/volumenes")
public class VolumenController {

    @Autowired
    private IVolumenService service;

    @GetMapping("/{url}")
    public ResponseEntity<?> buscarVolumen(@PathVariable String url){
        Map<String, Object> response = new HashMap<>();
        Volumen volumen = null;
        try {
            volumen = service.findByUrl(url);
            if (volumen != null) {
                return new ResponseEntity<>(volumen, HttpStatus.OK);
            } else {
                response.put("mensaje", "El volumen no se encontrĂ³ en la base de datos.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<?> mostrarFotoVolumen(@PathVariable Integer id) throws IOException {
        Volumen volumen = null;
        String nombre_foto = null;
        Map<String, Object> response = new HashMap<>();
        try {
            volumen = service.buscarVolumen(id);
            if (volumen != null) {
                nombre_foto = volumen.getFoto();
                if (nombre_foto != null) {
                    File img = new File("src/main/resources/static/fotos/volumenes/" + nombre_foto);
                    return ResponseEntity.ok()
                            .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img)))
                            .body(Files.readAllBytes(img.toPath()));
                } else {
                    response.put("mensaje", "El volumen que seleccionĂ³ no cuenta con foto.");
                    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
                }
            } else {
                response.put("mensaje", "El volumen no se encontrĂ³ en la base de datos.");
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
