package com.grupo07.notionary.service;

import com.grupo07.notionary.entity.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaService {
    public List<Tarea> findAll();
    public List<Tarea> findAllByUsuarioUsuario(String usuario);
    public Tarea findById(int id);
    public Tarea save(Tarea tarea);
    public Tarea deactivate(int id);
}
