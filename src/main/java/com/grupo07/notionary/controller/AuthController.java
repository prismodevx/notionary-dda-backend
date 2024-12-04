package com.grupo07.notionary.controller;

import com.grupo07.notionary.dto.auth.ApiResponseDto;
import com.grupo07.notionary.dto.auth.SignInRequestDto;
import com.grupo07.notionary.dto.auth.SignUpRequestDto;
import com.grupo07.notionary.exception.auth.RoleNotFoundException;
import com.grupo07.notionary.exception.auth.UserAlreadyExistsException;
import com.grupo07.notionary.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUpUser(signUpRequestDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponseDto<?>> signInUser(@RequestBody @Valid SignInRequestDto signInRequestDto){
        return authService.signInUser(signInRequestDto);
    }
}