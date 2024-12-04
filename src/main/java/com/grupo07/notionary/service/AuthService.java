package com.grupo07.notionary.service;

import com.grupo07.notionary.dto.auth.ApiResponseDto;
import com.grupo07.notionary.dto.auth.SignInRequestDto;
import com.grupo07.notionary.dto.auth.SignUpRequestDto;
import com.grupo07.notionary.exception.auth.RoleNotFoundException;
import com.grupo07.notionary.exception.auth.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<ApiResponseDto<?>> signUpUser(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException;
    ResponseEntity<ApiResponseDto<?>> signInUser(SignInRequestDto signInRequestDto);
}
