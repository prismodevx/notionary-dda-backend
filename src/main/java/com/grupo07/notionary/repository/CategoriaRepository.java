package com.grupo07.notionary.repository;

import com.grupo07.notionary.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByUsuario_UsuarioAndActivoTrue(String username);
}
