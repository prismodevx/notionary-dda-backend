package com.grupo07.notionary.seeder;

import com.grupo07.notionary.entity.Rol;
import com.grupo07.notionary.entity.enumeration.ERol;
import com.grupo07.notionary.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class RolDataSeeder {
    @Autowired
    private RolRepository repository;

    @EventListener
    @Transactional
    public void LoadRoles(ContextRefreshedEvent event) {

        List<ERol> roles = Arrays.stream(ERol.values()).toList();

        for(ERol erol: roles) {
            if (repository.findByNombre(erol)==null) {
                repository.save(new Rol(erol));
            }
        }

    }
}
