package com.grupo07.notionary.controller;

import com.grupo07.notionary.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
