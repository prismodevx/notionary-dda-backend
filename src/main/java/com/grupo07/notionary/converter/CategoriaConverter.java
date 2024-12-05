package com.grupo07.notionary.converter;

import com.grupo07.notionary.dto.CategoriaDto;
import com.grupo07.notionary.dto.TareaDto;
import com.grupo07.notionary.entity.Categoria;
import com.grupo07.notionary.entity.Tarea;
import com.grupo07.notionary.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class CategoriaConverter extends AbstractConverter<Categoria, CategoriaDto> {
    @Override
    public CategoriaDto fromEntity(Categoria entity) {
        if(entity == null) return null;

        return CategoriaDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .usuarioId(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .usuarioNombre(entity.getUsuario() != null ? entity.getUsuario().getUsuario() : null)
                .build();
    }

    @Override
    public Categoria fromDto(CategoriaDto dto) {
        if(dto == null) return null;

        Categoria categoria = Categoria.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .build();

        Usuario usuario = Usuario.builder().id(dto.getUsuarioId()).build();
        categoria.setUsuario(usuario);

        return categoria;
    }
}
