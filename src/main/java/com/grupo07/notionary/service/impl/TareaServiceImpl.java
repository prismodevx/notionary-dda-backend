package com.grupo07.notionary.service.impl;

import com.grupo07.notionary.entity.Tarea;
import com.grupo07.notionary.exception.GeneralException;
import com.grupo07.notionary.exception.NoDataFoundException;
import com.grupo07.notionary.exception.ValidateException;
import com.grupo07.notionary.repository.TareaRepository;
import com.grupo07.notionary.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TareaServiceImpl implements TareaService {
    @Autowired
    private TareaRepository repository;

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
}
