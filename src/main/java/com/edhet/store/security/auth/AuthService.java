package com.edhet.store.security.auth;

import com.edhet.store.security.jwt.JwtService;
import com.edhet.store.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final JwtService jwtService;

    public ResponseEntity<String> loginUser(LoginRequest request) {
        UserDetails user;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
            user = userService.loadUserByUsername(request.email());
        } catch (UsernameNotFoundException _e) {
            return ResponseEntity.status(404).body("No user with this email found");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
        return ResponseEntity.ok(jwtService.generateToken(user));
    }
}
