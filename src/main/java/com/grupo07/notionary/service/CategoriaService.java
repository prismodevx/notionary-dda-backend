package com.grupo07.notionary.service;

import com.grupo07.notionary.entity.Categoria;
import com.grupo07.notionary.entity.Tarea;

import java.util.List;

public interface CategoriaService {
    public List<Categoria> findAll();
    public List<Categoria> findAllByUsuarioUsuario(String usuario);
    public Categoria findById(int id);
    public Categoria save(Categoria categoria);
    public Categoria deactivate(int id);
}
