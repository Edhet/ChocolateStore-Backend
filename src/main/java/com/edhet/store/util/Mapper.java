package com.edhet.store.util;

import com.edhet.store.security.registration.RegistrationRequest;
import com.edhet.store.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Mapper {
    private final PasswordEncoder passwordEncoder;

    public User registrationRequestToUser(RegistrationRequest request) {
        return new User(
                request.firstName(),
                request.surname(),
                request.email(),
                passwordEncoder.encode(request.password()),
                request.birthDate(),
                request.gender()
        );
    }
}
