package com.edhet.store.security.login;

import com.edhet.store.error.exceptions.EntityNotFoundException;
import com.edhet.store.error.exceptions.WrongCredentialsException;
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

    public String login(LoginRequest request) throws WrongCredentialsException {
        UserDetails user;
        try {
            user = userService.getUser(request.email());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        } catch (AuthenticationException e) {
            throw new WrongCredentialsException("Invalid password");
        } catch (EntityNotFoundException e) {
            throw new WrongCredentialsException(e.getMessage());
        }
        return jwtService.generateToken(user);
    }
}
