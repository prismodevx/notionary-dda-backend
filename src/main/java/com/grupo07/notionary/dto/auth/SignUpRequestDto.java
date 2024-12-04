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
    @NotBlank(message = "Username es requerida!")
    private String usuario;

    @NotBlank(message = "Password es requerida!")
    private String password;

    private Set<String> roles;

    @Autowired
    public SignUpRequestDto(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
        this.roles = null;
    }
}
