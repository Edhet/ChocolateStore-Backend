package com.edhet.store.security.registration;

import com.edhet.store.user.Gender;

import java.time.LocalDate;

public record RegistrationRequest(
        String firstName,
        String surname,
        String email,
        String password,
        LocalDate birthDate,
        Gender gender
) {
}
