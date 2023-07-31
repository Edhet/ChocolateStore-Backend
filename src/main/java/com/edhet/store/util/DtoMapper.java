package com.edhet.store.util;

import com.edhet.store.security.registration.RegistrationRequest;
import com.edhet.store.user.User;
import com.edhet.store.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DtoMapper {
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

    public UserDTO userToDto(User user) {
        return new UserDTO(
                user.getFirstName(),
                user.getSurname(),
                user.getEmail(),
                user.getBirthDate(),
                user.getGender(),
                user.getOrders(),
                user.getPrefferedCategory(),
                user.getCreationTimestamp()
        );
    }
}
