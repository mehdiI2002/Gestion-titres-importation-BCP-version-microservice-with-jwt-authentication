package com.example.security_service.web;

import com.example.security_service.auth.AuthenticationResponse;
import com.example.security_service.auth.AuthenticationService;
import com.example.security_service.auth.RegisterRequest;
import com.example.security_service.config.JwtAuthentificationFilter;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;


@RestController
public class AuthentificationController {
    private AuthenticationService service ;
    private JwtAuthentificationFilter filter ;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthentificationFilter.class);

    public AuthentificationController(AuthenticationService service, JwtAuthentificationFilter filter) {
        this.service = service;
        this.filter = filter;

    }
    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
       return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/auth/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/auth/authenticatebytoken")
    public ResponseEntity<Map<String, Object>> authenticateByToken(HttpServletRequest request) {
        try {
            logger.info("Authenticating token for request: {}", request.getRequestURI());

            boolean authenticated = filter.authenticateByJwtToken(request);

            if (authenticated) {
                logger.info("Authentication successful");
                return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "Authentication successful",
                        "path", request.getRequestURI(),
                        "timestamp", System.currentTimeMillis()
                ));
            }

            logger.warn("Authentication failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "status", "error",
                    "message", "Authentication failed: Invalid or expired token",
                    "path", request.getRequestURI(),
                    "timestamp", System.currentTimeMillis()
            ));
        } catch (Exception e) {
            logger.error("Exception during authentication", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "status", "error",
                    "message", "Authentication failed: " + e.getMessage(),
                    "path", request.getRequestURI(),
                    "timestamp", System.currentTimeMillis()
            ));
        }
    }


}