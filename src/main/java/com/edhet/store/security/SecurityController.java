package com.edhet.store.security;

import com.edhet.store.security.auth.AuthService;
import com.edhet.store.security.auth.AuthenticationRequest;
import com.edhet.store.security.registration.RegistrationRequest;
import com.edhet.store.security.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SecurityConstants.SECURITY_API)
@AllArgsConstructor
public class SecurityController {

    private final RegistrationService registrationService;
    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        return authService.authenticateUser(request);
    }

    @PostMapping("/signup")
    public void register(@RequestBody RegistrationRequest request) {
        registrationService.register(request);
    }
}
