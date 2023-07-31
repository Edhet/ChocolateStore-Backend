package com.edhet.store.security.login;

import com.edhet.store.exception.errors.WrongPasswordException;
import com.edhet.store.security.jwt.JwtService;
import com.edhet.store.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;

    private final UserService userService;
    private final JwtService jwtService;

    public String login(LoginRequest request) throws WrongPasswordException {
        UserDetails user = userService.getUser(request.email());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        } catch (AuthenticationException e) {
            throw new WrongPasswordException("Invalid password");
        }

        return jwtService.generateToken(user);
    }
}
