package com.edhet.store.user;

import com.edhet.store.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

    public void addUser(User user) {
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
}