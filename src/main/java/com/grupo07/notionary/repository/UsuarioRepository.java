package com.grupo07.notionary.repository;

import com.grupo07.notionary.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {
    Optional<Usuario> findByUsuario(String username);
    Boolean existsByUsuario(String username);
}
