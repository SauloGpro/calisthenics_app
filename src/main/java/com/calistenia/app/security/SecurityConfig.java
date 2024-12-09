package com.calistenia.app.security;

import com.calistenia.app.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/api/users/register", "/api/users/login").permitAll() // Permitir registro y login
                        .requestMatchers(HttpMethod.GET, "/api/exercises/**").permitAll() // Permitir que cualquiera vea los ejercicios
                        .requestMatchers(HttpMethod.POST, "/api/exercises").hasAuthority("ROLE_ADMIN") // Solo admins pueden agregar
                        .requestMatchers(HttpMethod.DELETE, "/api/exercises/**").hasAuthority("ROLE_ADMIN") // Solo admins pueden eliminar
                        .requestMatchers(HttpMethod.POST, "/api/routines").hasAuthority("ROLE_ADMIN") // Solo admins pueden crear rutinas
                        .requestMatchers(HttpMethod.PUT, "/api/routines/**").hasAuthority("ROLE_ADMIN") // Solo admins pueden actualizar rutinas
                        .requestMatchers(HttpMethod.DELETE, "/api/routines/**").hasAuthority("ROLE_ADMIN") // Solo admins pueden eliminar rutinas
                        .anyRequest().authenticated() // Todas las demás peticiones requieren autenticación
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}






