package com.patientapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("anna")
                .password("{noop}azerty123") // {noop} indique aucun encodage
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        // À partir de Spring Security 6.1, désactiver CSRF se fait comme ceci :
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}