package by.it.dsmobile.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(configurer -> {
                    final var configurationSource = new UrlBasedCorsConfigurationSource();
                    final var configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of(
                            "http://localhost:8080",
                            "http://localhost:5173",
                            "http://localhost:3000",
                            "https://com.pass.app",
                            "capacitor://com.pass.app"
                    ));
                    configuration.addAllowedHeader(HttpHeaders.AUTHORIZATION);
                    configuration.addAllowedHeader(HttpHeaders.CONTENT_TYPE);
                    configuration.setAllowedMethods(List.of(
                            "GET", "POST", "PUT", "PATCH", "DELETE"
                    ));
                    configurationSource.registerCorsConfiguration("/**", configuration);
                    configurer.configurationSource(configurationSource);
                })
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/auth/**", "/v1/test/**").permitAll()
                        .requestMatchers("/v1/resources/xyz/photos/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs*/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
