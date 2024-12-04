package com.grupo07.notionary.controller;

import com.grupo07.notionary.converter.TareaConverter;
import com.grupo07.notionary.dto.TareaDto;
import com.grupo07.notionary.service.TareaService;
import com.grupo07.notionary.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tareas")
public class TareaController {
    @Autowired
    private TareaService service;

    @Autowired
    private TareaConverter converter;

    @GetMapping
    public ResponseEntity<List<TareaDto>> findAll() {
        List<TareaDto> registros = converter.fromEntities(service.findAll());
        return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<TareaDto>> findAll(@PathVariable("usuarioId") int usuarioId) {
        List<TareaDto> registros = converter.fromEntities(service.findAllByUsuarioId(usuarioId));
        return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
    }
}
