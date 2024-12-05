package com.grupo07.notionary.dto;

import com.grupo07.notionary.entity.Usuario;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TareaDto {
    private int id;
    private String titulo;
    private String descripcion;
    private long categoriaId;
    private String categoriaNombre;
    private long usuarioId;
    private String usuarioNombre;
}
