package com.example.security_service.service;

import com.example.security_service.Repository.UserRepository;
import com.example.security_service.models.AuthenticationRequest;
import com.example.security_service.models.AuthenticationResponse;
import com.example.security_service.models.RegisterRequest;
import com.example.security_service.user.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
private UserRepository userRepository;
private PasswordEncoder passwordEncoder;
private JwtService jwtService;
private  AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public AuthenticationResponse register(RegisterRequest request) {
        // creer user et enregistrer en bd et genrer le token
      User user = new User(null,request.getFirstName(),request.getLastName(),request.getEmail(),passwordEncoder.encode(request.getPassword()),request.getRole());
      userRepository.save(user);
     String jwtToken = jwtService.generateToken(user);
      return new AuthenticationResponse(jwtToken);
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
         authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  request.getEmail(),// email envoyer par user
                  request.getPassword()// pawssword envoyer par user
          )
        );
         User user = userRepository.findByEmail(request.getEmail())
                 .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return  new AuthenticationResponse(jwtToken);
    }

}
