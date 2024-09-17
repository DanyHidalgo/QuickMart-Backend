package com.ufm.QuickMart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/torneos").permitAll() // Permite acceso sin autenticación a /api/torneos
                                .requestMatchers("api/equipos/torneo/{torneoId}").permitAll()
                                .requestMatchers("api/predicciones").permitAll()
                                .anyRequest().authenticated() // Protege todos los demás endpoints
                )
                .csrf().disable() // Desactiva CSRF para pruebas (no recomendado para producción)
                .formLogin().disable() // Desactiva el formulario de inicio de sesión
                .httpBasic().disable(); // Desactiva la autenticación básica

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                if (username.equals("user")) {
                    return User.withUsername("user")
                            .password("{bcrypt}password")
                            .roles("USER")
                            .build();
                }
                throw new UsernameNotFoundException("User not found");
            }
        };
    }
}
