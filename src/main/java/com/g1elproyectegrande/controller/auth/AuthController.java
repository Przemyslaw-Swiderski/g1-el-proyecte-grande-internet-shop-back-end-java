package com.g1elproyectegrande.controller.auth;

import com.g1elproyectegrande.controller.auth.dto.JwtTokenRequest;
import com.g1elproyectegrande.controller.auth.dto.JwtTokenResponse;
import com.g1elproyectegrande.controller.auth.dto.UserRegistrationDto;
import com.g1elproyectegrande.entity.auth.Role;
import com.g1elproyectegrande.entity.auth.User;
import com.g1elproyectegrande.repository.auth.RoleRepository;
import com.g1elproyectegrande.repository.auth.UserRepository;
import com.g1elproyectegrande.service.auth.JwtTokenService;

//import jakarta.validation.Valid;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.g1elproyectegrande.config.auth.SpringSecurityConfig.DEVELOPER_READ;
import static com.g1elproyectegrande.config.auth.SpringSecurityConfig.APP_ADMIN;


@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

//    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
//    @PostMapping("/login")
//    public JwtTokenResponse login(@Valid @RequestBody JwtTokenRequest jwtTokenRequest) {
//        var authentication = new UsernamePasswordAuthenticationToken(
//                jwtTokenRequest.username(), jwtTokenRequest.password()
//        );
//        authenticationManager.authenticate(authentication);
//
//        return new JwtTokenResponse(jwtTokenService.generateToken(jwtTokenRequest.username()));
//    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public JwtTokenResponse login(@Valid @RequestBody JwtTokenRequest jwtTokenRequest) {
        var authentication = new UsernamePasswordAuthenticationToken(
                jwtTokenRequest.username(), jwtTokenRequest.password()
        );
        authenticationManager.authenticate(authentication);

        // Assuming you have a User object representing the authenticated user
        User user = userRepository.findByEmail(jwtTokenRequest.username()).orElseThrow();

        // Generate both access and refresh tokens
        String accessToken = jwtTokenService.generateAccessToken(jwtTokenRequest.username());
        String refreshToken = jwtTokenService.generateRefreshToken(user);
        String userEmail = user.getEmail();

        return new JwtTokenResponse(accessToken, refreshToken, userEmail);
    }


    //TODO ugly code
//    1. register method should return some dto with new user data, including uuid
//    2. Use some UserService/AuthService for registration, use PasswordEncoder in it
//    3. Above service should use repositories
//    4. Above service should use mapper to map DTOs and Entities

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "http://localhost:3000") // Specify the allowed origin(s)
    @PostMapping("/register")
    public void register(@Valid @RequestBody UserRegistrationDto newUserDto) {
        User newUser = new User();
        newUser.setEmail(newUserDto.email());
        newUser.setPassword(passwordEncoder.encode(newUserDto.password()));

        Role userRole = roleRepository.findByName(APP_ADMIN).get();
        newUser.addRole(userRole);
        userRole.addUser(newUser);

        userRepository.save(newUser);
    }
}