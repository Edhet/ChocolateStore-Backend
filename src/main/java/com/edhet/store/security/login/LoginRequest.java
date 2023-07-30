package com.edhet.store.security.login;

public record LoginRequest(
        String email,
        String password
) {
}
