package com.grupo07.notionary.dto.auth;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SignInResponseDto {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String usuario;
    private List<String> roles;
}
