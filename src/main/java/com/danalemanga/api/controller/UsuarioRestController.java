package com.danalemanga.api.controller;

import com.danalemanga.api.interfaceService.IUsuarioService;
import com.danalemanga.api.model.Rol;
import com.danalemanga.api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = {"*"})
public class UsuarioRestController {
    @Autowired
    private IUsuarioService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registro/cliente")
    public ResponseEntity<?> registrarCliente(@RequestBody Usuario u){
        Usuario nuevoUsuario = null;
        List<Rol> roles = new ArrayList<>();
        roles.add(new Rol(3, "ROLE_USER",null));
        String password = passwordEncoder.encode(u.getPassword());
        Map<String, Object> response = new HashMap<>();
        try{
            u.setPassword(password);
            u.setRoles(roles);
            nuevoUsuario = service.guardarUsuario(u);
            response.put("usuario", nuevoUsuario);
            response.put("mensaje", "El usuario fue registrado correctamente");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el registro a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
