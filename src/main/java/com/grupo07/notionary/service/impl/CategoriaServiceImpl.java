package com.grupo07.notionary.service.impl;

import com.grupo07.notionary.entity.Categoria;
import com.grupo07.notionary.entity.Tarea;
import com.grupo07.notionary.exception.GeneralException;
import com.grupo07.notionary.exception.NoDataFoundException;
import com.grupo07.notionary.exception.ValidateException;
import com.grupo07.notionary.repository.CategoriaRepository;
import com.grupo07.notionary.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    CategoriaRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        try {
            List<Categoria> registros = repository.findAll();
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
    public List<Categoria> findAllByUsuarioUsuario(String usuario) {
        try {
            List<Categoria> registros = repository.findAllByUsuario_UsuarioAndActivoTrue(usuario);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria findById(long id) {
        try {
            Categoria registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro como ese id"));
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public Categoria save(Categoria categoria) {
        try {
            if(categoria.getId() == 0) {
                categoria.setActivo(true);
                Categoria nuevo = repository.save(categoria);
                return nuevo;
            }

            Categoria registro = repository.findById((long) categoria.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese id"));
            registro.setNombre(categoria.getNombre());
            repository.save(registro);

            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public Categoria deactivate(long id) {
        try {
            Categoria registro = repository.findById(id)
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
