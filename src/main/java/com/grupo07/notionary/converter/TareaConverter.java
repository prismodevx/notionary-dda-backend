package com.grupo07.notionary.converter;

import com.grupo07.notionary.dto.TareaDto;
import com.grupo07.notionary.entity.Categoria;
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
                .categoriaId(entity.getCategoria() != null ? entity.getCategoria().getId() : null)
                .categoriaNombre(entity.getCategoria() != null ? entity.getCategoria().getNombre() : null)
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

        Categoria categoria = Categoria.builder().id((int) dto.getCategoriaId()).build();
        tarea.setCategoria(categoria);

        Usuario usuario = Usuario.builder().id(dto.getUsuarioId()).build();
        tarea.setUsuario(usuario);

        return tarea;
    }
}
