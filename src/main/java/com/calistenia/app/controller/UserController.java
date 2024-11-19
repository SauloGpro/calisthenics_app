package com.calistenia.app.controller;

import com.calistenia.app.controller.dto.UserDTO;
import com.calistenia.app.repository.entities.UserEntity;
import com.calistenia.app.service.UserService;

import com.calistenia.app.service.impl.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        log.info("Registrando usuario con email: {}", userDTO.getEmail());

        if (userService.findUserByEmail(userDTO.getEmail()) != null) {
            log.warn("El usuario con email {} ya existe", userDTO.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
        }

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        userService.addUser(userDTO);

        log.info("Usuario registrado exitosamente con email: {}", userDTO.getEmail());
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService.findUserEntityByEmail(userDTO.getEmail());

        if (userEntity != null && passwordEncoder.matches(userDTO.getPassword(), userEntity.getPassword())) {
            // Ahora pasamos tanto el email como el rol para generar el token
            String token = jwtConfig.generateToken(userDTO.getEmail(), userEntity.getRole().name());
            return ResponseEntity.ok(token); // Devolver el token JWT
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

}











