package com.example.authorization_service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@EnableConfigurationProperties(AuthProps.class)
public class AuthController {

    private final AuthProps props;

    public AuthController(AuthProps props) {
        this.props = props;
    }

    @PostMapping("/authorize")
    public ResponseEntity<String> authorize(@RequestHeader String username,
                                            @RequestHeader String password,
                                            @RequestHeader String role) {

        boolean ok = props.getUsers().values().stream()
                .anyMatch(c -> c.getUsername().equals(username)
                        && c.getPassword().equals(password)
                        && c.getRole().equalsIgnoreCase(role));

        return ok
                ? ResponseEntity.ok("✅ Authorized: " + username + " (" + role + ")")
                : ResponseEntity.status(403).body("❌ Unauthorized: invalid credentials");
    }
}
