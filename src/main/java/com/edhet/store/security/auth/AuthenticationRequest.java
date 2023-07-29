package com.edhet.store.security.auth;

public record AuthenticationRequest(
        String email,
        String password
) {
}
