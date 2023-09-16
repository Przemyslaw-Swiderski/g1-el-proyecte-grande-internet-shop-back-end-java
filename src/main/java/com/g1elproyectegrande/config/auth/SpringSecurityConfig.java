package com.g1elproyectegrande.config.auth;

import com.g1elproyectegrande.repository.auth.UserRepository;
import com.g1elproyectegrande.service.auth.JwtTokenService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.util.Collection;

@Configuration
@EnableConfigurationProperties(AuthConfigProperties.class)
@EnableMethodSecurity(jsr250Enabled = true)
public class SpringSecurityConfig {

    public static final String DEVELOPER_READ = "DEVELOPER_READ";
    public static final String DEVELOPER_WRITE = "DEVELOPER_WRITE";
    public static final String APP_ADMIN = "admin";


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationEntryPoint authenticationEntryPoint, JwtRequestFilter jwtRequestFilter) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/v1/login", "/api/v1/register").permitAll()
//                .anyRequest().authenticated()
//                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();


        http
                .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/v1/login", "/api/v1/register").permitAll()
                .anyRequest().authenticated()
                );

        return http.build();


    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("usersecret"))
                .roles(DEVELOPER_READ)
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("adminsecret"))
                .roles(DEVELOPER_READ, DEVELOPER_WRITE)
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByEmail(username)
                .map(u -> new UserDetails() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return u.getRoles().stream()
                                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName()))
                                .toList();
                    }

                    @Override
                    public String getPassword() {
                        return u.getPassword();
                    }

                    @Override
                    public String getUsername() {
                        return u.getEmail();
                    }

                    @Override
                    public boolean isAccountNonExpired() {
                        return true;
                    }

                    @Override
                    public boolean isAccountNonLocked() {
                        return true;
                    }

                    @Override
                    public boolean isCredentialsNonExpired() {
                        return true;
                    }

                    @Override
                    public boolean isEnabled() {
                        return true;
                    }
                })
                .orElseThrow(() -> new RuntimeException("Unexpected exception occurred"));
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        return new JwtRequestFilter(jwtTokenService, userDetailsService);
    }
}
