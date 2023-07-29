package com.edhet.store.security;

public final class SecurityConstants {
    public static final String SECRET = "BIG_SECRET_KEY";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 1_800_000;
    public static final String SECURITY_API = "api/v1/auth";
}
