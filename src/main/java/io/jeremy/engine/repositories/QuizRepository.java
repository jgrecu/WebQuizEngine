package io.jeremy.engine.repositories;

import io.jeremy.engine.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
