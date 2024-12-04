package com.grupo07.notionary.seeder;

import com.grupo07.notionary.entity.Rol;
import com.grupo07.notionary.entity.enumeration.ERol;
import com.grupo07.notionary.exception.auth.RoleNotFoundException;
import com.grupo07.notionary.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RolFactory {
    @Autowired
    RolRepository repository;

    public Rol getInstance(String rol) throws RoleNotFoundException {
        switch (rol) {
            case "usuario" -> {
                return repository.findByNombre(ERol.ROL_USUARIO);
            }
            default -> throw  new RoleNotFoundException("No se encontro el rol " +  rol);
        }
    }
}
