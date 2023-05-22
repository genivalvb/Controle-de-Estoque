package com.controle_estoqueapi.Controle.de.Estoque.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/**").permitAll()
                .antMatchers(HttpMethod.POST,"/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/**").permitAll()
                .antMatchers(HttpMethod.PATCH,"/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

        return http.build();

/*
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults());*/
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails genival = User.builder()
                .username("genival")
                .password(passwordEncoder().encode("januario"))
                .roles("ADMIN")
                .build();

        UserDetails melina = User.builder()
                .username("melina")
                .password(passwordEncoder().encode("januario"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(genival, melina);
    }
}
