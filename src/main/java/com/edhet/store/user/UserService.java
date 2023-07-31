package com.edhet.store.user;

import com.edhet.store.exception.errors.EmailTakenException;
import com.edhet.store.exception.errors.InvalidBirthDateException;
import com.edhet.store.exception.errors.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User getUser(String email) throws UserNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("No user with email: " + email));
    }

    public User getUser(Long id) throws UserNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("No user with id: " + id));
    }

    public void addUser(User user) throws EmailTakenException {
        if (!validDate(user.getBirthDate()))
            throw new InvalidBirthDateException("Inserted date is after today");
        if (userRepository.existsByEmail(user.getEmail()))
            throw new EmailTakenException("Email " + user.getEmail() + " has been taken");
        userRepository.save(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException("No user with id: " + id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with email: " + username));
    }

    private Boolean validDate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }
}