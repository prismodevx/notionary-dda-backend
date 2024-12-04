package com.grupo07.notionary.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRequestDto {
    @NotBlank(message = "Username requerido")
    private String usuario;

    @NotBlank(message = "Contrasenia requerida")
    private String password;
}
