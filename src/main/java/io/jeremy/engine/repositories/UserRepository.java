package io.jeremy.engine.repositories;

import io.jeremy.engine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameIgnoreCase(String email);

    Optional<User> findByUsername(String email);
}
