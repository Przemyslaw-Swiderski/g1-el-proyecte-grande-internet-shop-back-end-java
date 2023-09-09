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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.g1elproyectegrande.config.auth.SpringSecurityConfig.DEVELOPER_READ;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenService jwtTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public JwtTokenResponse login(@Valid @RequestBody JwtTokenRequest jwtTokenRequest) {
        var authentication = new UsernamePasswordAuthenticationToken(
                jwtTokenRequest.username(), jwtTokenRequest.password()
        );
        authenticationManager.authenticate(authentication);

        return new JwtTokenResponse(jwtTokenService.generateToken(jwtTokenRequest.username()));
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

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserRegistrationDto newUserDto) {
        User newUser = new User();
        newUser.setEmail(newUserDto.email());
        newUser.setPassword(passwordEncoder.encode(newUserDto.password()));

        Role userRole = roleRepository.findByName(DEVELOPER_READ).get();
        newUser.addRole(userRole);
        userRole.addUser(newUser);

        userRepository.save(newUser);
    }
}