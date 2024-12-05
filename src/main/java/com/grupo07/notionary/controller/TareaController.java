package com.grupo07.notionary.controller;

import com.grupo07.notionary.converter.TareaConverter;
import com.grupo07.notionary.dto.TareaDto;
import com.grupo07.notionary.entity.Tarea;
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
@RequestMapping("/api/v1/tareas")
public class TareaController {
    @Autowired
    private TareaService service;

    @Autowired
    private TareaConverter converter;

    @Autowired
    private PdfService pdfService;

//    @GetMapping
//    public ResponseEntity<List<TareaDto>> findAll() {
//        List<TareaDto> registros = converter.fromEntities(service.findAll());
//        return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<TareaDto>> findAllByUsuario(@RequestParam("usuario") String usuario) {
        List<TareaDto> registros = converter.fromEntities(service.findAllByUsuarioUsuario(usuario));
        return new WrapperResponse(true, "success", registros).createResponse(HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<TareaDto>> findById(@PathVariable("id") int id) {
        TareaDto dto = converter.fromEntity(service.findById(id));
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<TareaDto> create(@RequestBody TareaDto registro) {
        Tarea entity = converter.fromDto(registro);
        TareaDto dto = converter.fromEntity(service.save(entity));
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TareaDto> deactivate(@PathVariable("id") int id) {
        TareaDto dto = converter.fromEntity(service.deactivate(id));
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @PutMapping("/{usuario}")
    public ResponseEntity<TareaDto> update(@PathVariable("usuario") String usuario, @RequestBody TareaDto registro) {
        Tarea entity = converter.fromDto(registro);
        TareaDto dto = converter.fromEntity(service.save(entity));
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> generateReport(@RequestParam("usuario") String usuario) {
        List<TareaDto> tareas = converter.fromEntities(service.findAllByUsuarioUsuario(usuario));

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHora = fecha.format(formato);

        // crear el contexto de Thymeleaf con los datos
        Context context = new Context();
        context.setVariable("registros", tareas);
        context.setVariable("fecha", fechaHora);

        // Llamar al servicio PdfService para generar el PDF
        byte[] pdfBytes = pdfService.createPdf("tareasReporte", context);

        // Configurar los encabezados de la respuesta HTTP para devolver el PDF
        // import org.springframework.http.HttpHeaders;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "reporte_tareas.pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}
