package com.danalemanga.api.controller;

import com.danalemanga.api.interfaceService.IUsuarioService;
import com.danalemanga.api.model.Rol;
import com.danalemanga.api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    public ResponseEntity<?> registrarCliente(@RequestBody Usuario u) {
        Usuario nuevoUsuario = null;
        List<Rol> roles = new ArrayList<>();
        roles.add(new Rol(3, "ROLE_USER", null));
        String password = passwordEncoder.encode(u.getPassword());
        Map<String, Object> response = new HashMap<>();
        try {
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

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public Page<Usuario> listarUsuarios(@PathVariable Integer page) {
        return service.paginacionUsuarios(PageRequest.of(page, 15));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Integer id) {
        Usuario usuario = null;
        Map<String, Object> response = new HashMap<>();
        try {
            usuario = service.buscarUsuario(id);
            if (usuario != null) {
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            } else {
                response.put("mensaje", "El usuario no se encontró en la base de datos");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta a la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
