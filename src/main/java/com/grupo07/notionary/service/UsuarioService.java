package com.grupo07.notionary.service;

import com.grupo07.notionary.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
    boolean existsByUsuario(String username);
    public Usuario findById(int id);
    void save(Usuario usuario);
}
