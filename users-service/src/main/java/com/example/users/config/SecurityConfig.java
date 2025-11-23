package com.example.users.config;

import com.example.users.servicios.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    /**
     * Configuración principal de seguridad HTTP.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Para simplificar pruebas con Postman
                .csrf(csrf -> csrf.disable())

                // Sin sesión de servidor (estilo API REST)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Qué endpoints son públicos y cuáles requieren auth
                .authorizeHttpRequests(auth -> auth
                        // Actuator, Swagger, auth (registro / futuro login) públicos
                        .requestMatchers(
                                "/actuator/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api/auth/**"
                        ).permitAll()
                        // Todo lo demás requiere autenticación
                        .anyRequest().authenticated()
                )

                // Autenticación vía credenciales contra nuestra BD (UserService)
                .authenticationProvider(daoAuthenticationProvider())

                // Por ahora usamos HTTP Basic para probar fácilmente con Postman
                .httpBasic(httpBasic -> {});

        return http.build();
    }

    /**
     * PasswordEncoder que usaremos para encriptar y validar passwords.
     * Debe ser el mismo que usas al guardar usuarios (BCrypt).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Provider que conecta Spring Security con nuestro UserService
     * (que implementa UserDetailsService).
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * AuthenticationManager que usaremos si más adelante quieres un endpoint
     * de /login que autentique "a mano".
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }
}
