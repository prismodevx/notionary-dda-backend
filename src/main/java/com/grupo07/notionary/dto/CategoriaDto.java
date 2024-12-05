package com.grupo07.notionary.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDto {
    private int id;
    private String nombre;
    private long usuarioId;
    private String usuarioNombre;
}
