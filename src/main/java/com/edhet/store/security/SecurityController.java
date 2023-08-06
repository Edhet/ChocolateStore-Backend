package com.edhet.store.security;

import com.edhet.store.security.login.JwtResponse;
import com.edhet.store.security.login.LoginRequest;
import com.edhet.store.security.login.LoginService;
import com.edhet.store.security.registration.RegistrationRequest;
import com.edhet.store.security.registration.RegistrationService;
import com.edhet.store.util.DtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SecurityConstants.SECURITY_API)
@AllArgsConstructor
public class SecurityController {

    private final DtoMapper dtoMapper;
    private final RegistrationService registrationService;
    private final LoginService loginService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        return dtoMapper.jwtToResponse(loginService.login(request));
    }

    @PostMapping("/signup")
    public void register(@RequestBody RegistrationRequest request) {
        registrationService.register(request);
    }

    @GetMapping
    public void runAuthFilter() {
    }
}
