package com.danalemanga.api.service;

import com.danalemanga.api.interfaceService.IUsuarioService;
import com.danalemanga.api.model.Usuario;
import com.danalemanga.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Page<Usuario> paginacionUsuarios(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Usuario guardarUsuario(Usuario u) {
        return repository.save(u);
    }

    @Override
    public void eliminarUsuario(int id) {
        repository.deleteById(id);
    }

    @Override
    public Usuario buscarUsuario(int id) {
        return repository.findById(id).orElse(null);
    }
}
