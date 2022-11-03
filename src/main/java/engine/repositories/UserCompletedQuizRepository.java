package engine.repositories;

import engine.model.UserCompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserCompletedQuizRepository extends JpaRepository<UserCompletedQuiz, Long> {

    Page<UserCompletedQuiz> findAllByUserName( String username, Pageable pageRequest);
}
