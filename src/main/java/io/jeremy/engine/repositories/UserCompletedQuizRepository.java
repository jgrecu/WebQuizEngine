package io.jeremy.engine.repositories;

import io.jeremy.engine.model.UserCompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCompletedQuizRepository extends JpaRepository<UserCompletedQuiz, Long> {

    Page<UserCompletedQuiz> findAllByUserName( String username, Pageable pageRequest);
}
