package br.csi.barbeariabarbadus.infra.security;

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
public class SecurityConfig {

    private final AutenticacaoFilter autenticacaoFilter;
    public SecurityConfig(AutenticacaoFilter filtro){
        this.autenticacaoFilter = filtro;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http

                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->
                        auth.requestMatchers(HttpMethod.POST, "/login").permitAll()
                                // usuario
//                                .requestMatchers(HttpMethod.GET, "/usuario/**").hasAnyAuthority("ROLE_ADMIN","ROLE_FUNCIONARIO")
                                .requestMatchers(HttpMethod.POST, "/usuario").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/usuario").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/usuario").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/usuario").hasAuthority("ROLE_ADMIN")

                                // produto
                                .requestMatchers(HttpMethod.POST, "/permissao").hasAuthority("ROLE_ADMIN")

                                .requestMatchers(HttpMethod.GET, "/produto").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/permissao").hasAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/usuario").hasAuthority("ROLE_FUNCIONARIO")
                                .requestMatchers(HttpMethod.GET, "/usuario").hasAuthority("ROLE_CLIENTE")
                                .requestMatchers(HttpMethod.GET, "/usuario").hasAuthority("ROLE_FUNCIONARIO")
//                                .requestMatchers(HttpMethod.POST, "/produto/**").hasAnyAuthority("ROLE_CLIENTE", "ROLE_ADMIN","ROLE_FUNCIONARIO")
//                                .requestMatchers(HttpMethod.GET, "/produto/listar").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/permissao/listar").permitAll()

                                .anyRequest().authenticated())
                .addFilterBefore(this.autenticacaoFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(crsf-> crsf.disable())
                .build();


    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
