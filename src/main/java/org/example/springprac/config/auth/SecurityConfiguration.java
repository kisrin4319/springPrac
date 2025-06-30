package org.example.springprac.config.auth;

import lombok.RequiredArgsConstructor;
import org.example.springprac.domain.user.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) ->
                        csrfConfig.disable())
                .headers((headersConfig) ->
                        headersConfig.frameOptions((frameOptionsConfig) ->
                                        frameOptionsConfig.disable()
                        )
                )
                .authorizeHttpRequests((authorizeConfig) ->
                                authorizeConfig
                                        .requestMatchers("/").permitAll()
                                        .requestMatchers("/css/**").permitAll()
                                        .requestMatchers("/images/**").permitAll()
                                        .requestMatchers("/h2-console/**").permitAll()
                                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                                        .anyRequest().authenticated()

                                )
                .logout((logoutConfig) ->
                        logoutConfig.logoutSuccessUrl("/"))
                .oauth2Login((oauth2LoginConfig) ->
                        oauth2LoginConfig.userInfoEndpoint((userInfoEndpointConfig) ->
                                userInfoEndpointConfig.userService(customOAuth2UserService)));

        return http.build();
    }
}
