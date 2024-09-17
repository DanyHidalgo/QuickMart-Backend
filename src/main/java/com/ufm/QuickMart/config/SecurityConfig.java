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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors() // Habilita CORS
                .and()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/api/torneos").permitAll() // Permite acceso sin autenticación a /api/torneos
                                .requestMatchers("/api/equipos/torneo/{torneoId}").permitAll() //ver torneo por id
                                .requestMatchers("/api/predicciones").permitAll() // todas las predicciones
                                .requestMatchers("/api/usuarios/{usuarioId}/grupos").permitAll() // ver grupos por id de usuario
                                .requestMatchers("/api/usuarios/grupo/{grupoId}/clasificacion").permitAll() //ver la calsificacion de grupo
                                .requestMatchers("/api/hola").permitAll()
                                .requestMatchers("/api/usuarios/{usuarioId}/grupos/{grupoId}").permitAll() //meter usuario en grupo
                                .requestMatchers("/api/usuarios/{id}").permitAll() //ver usuario por id
                                .requestMatchers("/api/predicciones/partido/{partidoId}").permitAll() //ver predicciones de partido
                                .requestMatchers("/api/usuario-grupo/{usuarioId}/{grupoId}").permitAll() //ver puntos de usuario en un grupo
                                .requestMatchers("/api/partidos/{id}").permitAll() //actualizar resultado de partido
                                .requestMatchers("/api/usuarios/partido/{partidoId}/actualizar-puntos").permitAll() //puede ser post o put actualiza partido por id


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
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Ajusta según tu dominio de frontend
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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
