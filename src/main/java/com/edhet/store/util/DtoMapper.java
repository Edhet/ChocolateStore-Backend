package com.edhet.store.util;

import com.edhet.store.exception.errors.BadRegistrationRequestException;
import com.edhet.store.security.registration.RegistrationRequest;
import com.edhet.store.user.User;
import com.edhet.store.user.info.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DtoMapper {
    private final PasswordEncoder passwordEncoder;

    public User registrationRequestToUser(RegistrationRequest request) throws BadRegistrationRequestException {
        User user;
        try {
            user = new User(
                    request.firstName(),
                    request.surname(),
                    request.email(),
                    passwordEncoder.encode(request.password()),
                    request.birthDate(),
                    request.gender()
            );
        } catch (IllegalArgumentException e) {
            throw new BadRegistrationRequestException("password field is null");
        } catch (NullPointerException e) {
            String field = e.getMessage().split(" ")[0];
            throw new BadRegistrationRequestException(field + " field is null");
        }
        return user;
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
