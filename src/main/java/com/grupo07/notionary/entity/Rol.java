package com.grupo07.notionary.entity;

import com.grupo07.notionary.entity.enumeration.ERol;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERol nombre;

    public Rol(ERol nombre) {
        this.nombre = nombre;
    }
}
