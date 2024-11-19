package com.calistenia.app.service.impl;

import com.calistenia.app.controller.dto.UserDTO;
import com.calistenia.app.repository.UserRepository;
import com.calistenia.app.repository.entities.Role;
import com.calistenia.app.repository.entities.UserEntity;
import com.calistenia.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final JwtConfig jwtConfig; // Inyectar JwtConfig para manejo de tokens

    @Override
    public UserEntity findUserEntityByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userEntity -> new UserDTO(userEntity.getName(), userEntity.getEmail(), null))
                .orElse(null);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());

        // Asignar el rol ROLE_USER por defecto
        userEntity.setRole(Role.ROLE_USER);

        userRepository.save(userEntity);

        return userDTO;
    }

    // Método para generar un token JWT para un usuario con su rol
    public String generateTokenForUser(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Generar el token con el rol del usuario
        return jwtConfig.generateToken(user.getEmail(), user.getRole().name());
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return findUserEntityByEmail(email); // Asegúrate de que UserEntity implementa UserDetails
    }
}










