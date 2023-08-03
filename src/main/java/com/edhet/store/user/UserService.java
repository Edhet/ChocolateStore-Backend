package com.edhet.store.user;

import com.edhet.store.exception.errors.EntityNotFoundException;
import com.edhet.store.exception.errors.InvalidDateException;
import com.edhet.store.exception.errors.UniqueDatabaseFieldException;
import com.edhet.store.security.SecurityConstants;
import com.edhet.store.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public User getUserFromJwt(String authHeader) {
        final String jwtToken = authHeader.substring(SecurityConstants.TOKEN_PREFIX.length() - 1);
        final String email = jwtService.extractEmail(jwtToken);

        return this.getUser(email);
    }

    public User getUser(String email) throws EntityNotFoundException {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("No user with email: " + email));
    }

    public User getUser(Long id) throws EntityNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with id: " + id));
    }

    public void addUser(User user) throws UniqueDatabaseFieldException {
        if (!validDate(user.getBirthDate()))
            throw new InvalidDateException("Inserted date is after today");
        if (userRepository.existsByEmail(user.getEmail()))
            throw new UniqueDatabaseFieldException("Email " + user.getEmail() + " has been taken");
        userRepository.save(user);
    }

    public void deleteUser(Long id) throws EntityNotFoundException {
        if (!userRepository.existsById(id))
            throw new EntityNotFoundException("No user with id: " + id);
        userRepository.deleteById(id);
    }

    private Boolean validDate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user with email: " + username));
    }
}