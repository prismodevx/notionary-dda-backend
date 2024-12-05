package com.grupo07.notionary.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @NotBlank(message = "Usuario es requerido!")
    private String usuario;

    @NotBlank(message = "Email es requerido!")
    private String email;

    @NotBlank(message = "Nombres es requerido!")
    private String nombres;

    @NotBlank(message = "Password es requerida!")
    private String password;

    private Set<String> roles;

    @Autowired
    public SignUpRequestDto(String usuario, String email, String nombres, String password) {
        this.usuario = usuario;
        this.email = email;
        this.nombres = nombres;
        this.password = password;
        this.roles = null;
    }
}
