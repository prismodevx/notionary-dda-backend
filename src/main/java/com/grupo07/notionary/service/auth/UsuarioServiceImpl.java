package com.grupo07.notionary.service.auth;

import com.grupo07.notionary.entity.Usuario;
import com.grupo07.notionary.exception.GeneralException;
import com.grupo07.notionary.exception.NoDataFoundException;
import com.grupo07.notionary.exception.ValidateException;
import com.grupo07.notionary.repository.UsuarioRepository;
import com.grupo07.notionary.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository userRepository;

    @Override
    public boolean existsByUsuario(String username) {
        return userRepository.existsByUsuario(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(int id) {
        try {
            Usuario registro = userRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public void save(Usuario user){
        userRepository.save(user);
    }
}
