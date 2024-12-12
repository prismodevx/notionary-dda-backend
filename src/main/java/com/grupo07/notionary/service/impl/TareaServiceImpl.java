package com.grupo07.notionary.service.impl;

import com.grupo07.notionary.entity.Categoria;
import com.grupo07.notionary.entity.Tarea;
import com.grupo07.notionary.exception.GeneralException;
import com.grupo07.notionary.exception.NoDataFoundException;
import com.grupo07.notionary.exception.ValidateException;
import com.grupo07.notionary.repository.CategoriaRepository;
import com.grupo07.notionary.repository.TareaRepository;
import com.grupo07.notionary.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {
    @Autowired
    private TareaRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Tarea> findAll() {
        try {
            List<Tarea> registros = repository.findAll();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidorr");
        } catch (Exception e) {
            throw new GeneralException("xd");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tarea> findAllByUsuarioUsuario(String username) {
        try {
            List<Tarea> registros = repository.findAllByUsuario_UsuarioAndActivoTrueOrderByCreatedAtDesc(username);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Tarea findById(int id) {
        try {
            Tarea registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public Tarea save(Tarea tarea) {
        try {
            if(tarea.getId() == 0) {
                tarea.setActivo(true);
                Tarea nuevo = repository.save(tarea);
                return nuevo;
            }

            Categoria categoria = categoriaRepository.findById((long) tarea.getCategoria().getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe una categoría con ese id"));
            tarea.setCategoria(categoria);

            Tarea registro = repository.findById(tarea.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese id"));
            registro.setTitulo(tarea.getTitulo());
            registro.setDescripcion(tarea.getDescripcion());
            registro.setEstado(tarea.getEstado());
            registro.setCategoria(categoria);
            registro.setDuracion(tarea.getDuracion());
            repository.save(registro);

            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public Tarea deactivate(int id) {
        try {
            Tarea registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
            registro.setActivo(false);
            repository.save(registro);
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }
}
