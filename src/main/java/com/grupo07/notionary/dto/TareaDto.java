package com.grupo07.notionary.dto;

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
}
