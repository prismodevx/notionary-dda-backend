package com.grupo07.notionary.converter;

import com.grupo07.notionary.dto.TareaDto;
import com.grupo07.notionary.entity.Tarea;
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
                .build();
    }

    @Override
    public Tarea fromDto(TareaDto dto) {
        if(dto == null) return null;

        return Tarea.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .build();
    }
}
