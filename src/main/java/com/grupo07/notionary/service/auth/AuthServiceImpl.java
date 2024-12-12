package com.grupo07.notionary.service.auth;

import com.grupo07.notionary.config.jwt.JwtUtils;
import com.grupo07.notionary.dto.auth.ApiResponseDto;
import com.grupo07.notionary.dto.auth.SignInRequestDto;
import com.grupo07.notionary.dto.auth.SignInResponseDto;
import com.grupo07.notionary.dto.auth.SignUpRequestDto;
import com.grupo07.notionary.entity.Rol;
import com.grupo07.notionary.entity.Usuario;
import com.grupo07.notionary.exception.auth.RoleNotFoundException;
import com.grupo07.notionary.exception.auth.UserAlreadyExistsException;
import com.grupo07.notionary.seeder.RolFactory;
import com.grupo07.notionary.service.AuthService;
import com.grupo07.notionary.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RolFactory rolFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public ResponseEntity<ApiResponseDto<?>> signUpUser(SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        if (usuarioService.existsByUsuario(signUpRequestDto.getUsuario())) {
            throw new UserAlreadyExistsException("Error en el registro: el nombre de usuario proporcionado ya existe. Intente iniciar sesión o proporcione otro nombre de usuario.");
        }

        Usuario user = createUser(signUpRequestDto);
        usuarioService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("Usuario correctamente registrado!")
                        .build()
        );
    }

    private Usuario createUser(SignUpRequestDto signUpRequestDto) throws RoleNotFoundException {
        return Usuario.builder()
                .usuario(signUpRequestDto.getUsuario())
                .email(signUpRequestDto.getEmail())
                .nombres(signUpRequestDto.getNombres())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .activo(true)
                .roles(determineRoles(signUpRequestDto.getRoles()))
                .build();
    }

    private Set<Rol> determineRoles(Set<String> strRoles) throws RoleNotFoundException {
        Set<Rol> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(rolFactory.getInstance("usuario"));
        } else {
            for (String role : strRoles) {
                roles.add(rolFactory.getInstance(role));
            }
        }
        return roles;
    }

    @Override
    public ResponseEntity<ApiResponseDto<?>> signInUser(SignInRequestDto signInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDto.getUsuario(), signInRequestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        SignInResponseDto signInResponseDto = SignInResponseDto.builder()
                .usuario(userDetails.getUsername())
                .id(userDetails.getId())
                .token(jwt)
                .type("Bearer")
                .roles(roles)
                .build();

        return ResponseEntity.ok(
                ApiResponseDto.builder()
                        .isSuccess(true)
                        .message("Inicio de sesion satisfactorio!")
                        .response(signInResponseDto)
                        .build()
        );
    }
}
