package io.jeremy.engine.services;

import io.jeremy.engine.model.SecurityUser;
import io.jeremy.engine.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsernameIgnoreCase(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username \"" + username + "\" not found"));
    }
}
