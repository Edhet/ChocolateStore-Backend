package com.edhet.store.security.registration;

import com.edhet.store.user.User;
import com.edhet.store.user.UserService;
import com.edhet.store.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final Mapper mapper;
    private final UserService userService;

    public void register(RegistrationRequest request) {
        User newUser = mapper.registrationRequestToUser(request);
        userService.addUser(newUser);
    }
}
