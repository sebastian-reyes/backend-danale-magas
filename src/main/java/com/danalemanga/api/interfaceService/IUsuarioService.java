package com.danalemanga.api.interfaceService;

import com.danalemanga.api.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioService {
    public Usuario findByEmail(String email);
    public Page<Usuario> paginacionUsuarios(Pageable pageable);
    public Usuario guardarUsuario(Usuario u);
    public void eliminarUsuario(int id);
    public Usuario buscarUsuario(int id);
}
