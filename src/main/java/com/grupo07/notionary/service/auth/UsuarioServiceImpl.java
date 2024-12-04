package com.grupo07.notionary.service.auth;

import com.grupo07.notionary.entity.Usuario;
import com.grupo07.notionary.repository.UsuarioRepository;
import com.grupo07.notionary.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository userRepository;

    @Override
    public boolean existsByUsuario(String username) {
        return userRepository.existsByUsuario(username);
    }

    @Override
    public void save(Usuario user){
        userRepository.save(user);
    }
}
