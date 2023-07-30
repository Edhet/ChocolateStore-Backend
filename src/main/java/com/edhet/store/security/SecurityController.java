package com.edhet.store.security;

import com.edhet.store.security.login.LoginRequest;
import com.edhet.store.security.login.LoginService;
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
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }

    @PostMapping("/signup")
    public void register(@RequestBody RegistrationRequest request) {
        registrationService.register(request);
    }
}
