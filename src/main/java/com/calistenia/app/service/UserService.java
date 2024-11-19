package com.calistenia.app.service;

import com.calistenia.app.controller.dto.UserDTO;
import com.calistenia.app.repository.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity findUserEntityByEmail(String email);
    UserDTO findUserByEmail(String email);
    UserDTO addUser(UserDTO userDTO);

    String generateTokenForUser(String email);

}

