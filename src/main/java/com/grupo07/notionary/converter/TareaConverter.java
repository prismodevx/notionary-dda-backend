package com.grupo07.notionary.converter;

import com.grupo07.notionary.dto.TareaDto;
import com.grupo07.notionary.entity.Tarea;
import com.grupo07.notionary.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class TareaConverter extends AbstractConverter<Tarea, TareaDto> {
    @Override
    public TareaDto fromEntity(Tarea entity) {
        if(entity == null) return null;

        return TareaDto.builder()
                .id(entity.getId())
                .titulo(entity.getTitulo())
                .descripcion(entity.getDescripcion())
                .usuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .usuarioNombre(entity.getUsuario() != null ? entity.getUsuario().getUsuario() : null)
                .build();
    }

    @Override
    public Tarea fromDto(TareaDto dto) {
        if(dto == null) return null;

        Tarea tarea = Tarea.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .build();

        Usuario usuario = Usuario.builder().id(dto.getUsuarioId()).build();
        tarea.setUsuario(usuario);

        return tarea;
    }
}
