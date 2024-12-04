package com.grupo07.notionary.repository;

import com.grupo07.notionary.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
//    List<Tarea> findByTitulo(String titulo);
}
