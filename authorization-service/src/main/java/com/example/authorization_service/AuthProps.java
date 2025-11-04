package com.example.authorization_service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "security.productcomposite")
public class AuthProps {

    public static class Cred {
        private String username;
        private String password;
        private String role;

        // Getters et Setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    private Map<String, Cred> users = new HashMap<>();

    public Map<String, Cred> getUsers() { return users; }
    public void setUsers(Map<String, Cred> users) { this.users = users; }
}
