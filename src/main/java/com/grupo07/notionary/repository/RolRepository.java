package com.grupo07.notionary.repository;

import com.grupo07.notionary.entity.Rol;
import com.grupo07.notionary.entity.enumeration.ERol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(ERol name);
}
