package com.edhet.store.security.auth;

public record LoginRequest(
        String email,
        String password
) {
}
