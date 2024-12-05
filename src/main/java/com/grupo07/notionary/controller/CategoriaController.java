package com.grupo07.notionary.controller;

import com.grupo07.notionary.converter.CategoriaConverter;
import com.grupo07.notionary.converter.TareaConverter;
import com.grupo07.notionary.dto.CategoriaDto;
import com.grupo07.notionary.dto.TareaDto;
import com.grupo07.notionary.entity.Categoria;
import com.grupo07.notionary.entity.Tarea;
import com.grupo07.notionary.service.CategoriaService;
import com.grupo07.notionary.service.PdfService;
import com.grupo07.notionary.service.TareaService;
import com.grupo07.notionary.util.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService service;

    @Autowired
    private CategoriaConverter converter;

    @Autowired
    private PdfService pdfService;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAllByUsuario(@RequestParam("usuario") String usuario) {
        List<CategoriaDto> registros = converter.fromEntities(service.findAllByUsuarioUsuario(usuario));
        return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CategoriaDto>> findById(@PathVariable("id") int id) {
        CategoriaDto dto = converter.fromEntity(service.findById(id));
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> create(@RequestBody CategoriaDto registro) {
        Categoria entity = converter.fromDto(registro);
        CategoriaDto dto = converter.fromEntity(service.save(entity));
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @PutMapping("/{usuario}")
    public ResponseEntity<CategoriaDto> update(@PathVariable("usuario") String usuario, @RequestBody CategoriaDto registro) {
        Categoria entity = converter.fromDto(registro);
        CategoriaDto dto = converter.fromEntity(service.save(entity));
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDto> deactivate(@PathVariable("id") int id) {
        CategoriaDto dto = converter.fromEntity(service.deactivate(id));
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

//    Reporteria
//    @GetMapping("/report")
//    public ResponseEntity<byte[]> generateReport(@RequestParam("usuario") String usuario) {
//        List<TareaDto> tareas = converter.fromEntities(service.findAllByUsuarioUsuario(usuario));
//
//        LocalDateTime fecha = LocalDateTime.now();
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String fechaHora = fecha.format(formato);
//
//        Context context = new Context();
//        context.setVariable("registros", tareas);
//        context.setVariable("fecha", fechaHora);
//
//        byte[] pdfBytes = pdfService.createPdf("tareasReporte", context);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDispositionFormData("inline", "reporte_tareas.pdf");
//
//        return ResponseEntity.ok().headers(headers).body(pdfBytes);
//    }
}
