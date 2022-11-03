package io.jeremy.engine.services;

import io.jeremy.engine.dtos.requests.AnswerRequest;
import io.jeremy.engine.dtos.requests.QuizRequest;
import io.jeremy.engine.dtos.requests.UserRegistrationRequest;
import io.jeremy.engine.dtos.responses.AnswerResponse;
import io.jeremy.engine.dtos.responses.CompletedQuizResponse;
import io.jeremy.engine.dtos.responses.QuizResponse;
import io.jeremy.engine.model.Quiz;
import io.jeremy.engine.model.User;
import io.jeremy.engine.model.UserCompletedQuiz;
import io.jeremy.engine.repositories.QuizRepository;
import io.jeremy.engine.repositories.UserCompletedQuizRepository;
import io.jeremy.engine.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final UserCompletedQuizRepository userCompletedQuizRepository;
    private final PasswordEncoder passwordEncoder;

    public QuizService(QuizRepository quizRepository, UserRepository userRepository,
                       UserCompletedQuizRepository userCompletedQuizRepository, PasswordEncoder passwordEncoder) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.userCompletedQuizRepository = userCompletedQuizRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public QuizResponse getQuizById(long id) {
        return quizRepository.findById(id)
                .map(QuizResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Quiz with id %d not found!", id)));
    }

    public Page<QuizResponse> getAllQuizzes(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("id"));

        Page<Quiz> quizzes = quizRepository.findAll(pageRequest);
        return quizzes.map(QuizResponse::new);

    }

    public Page<CompletedQuizResponse> getAllUsersCompleted(Integer page, String name) {
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Pageable pageRequest = PageRequest.of(page, 10, Sort.by("completedAt").descending());


        Page<UserCompletedQuiz> completed = userCompletedQuizRepository.findAllByUserName(name, pageRequest);
        return completed.map(CompletedQuizResponse::new);
    }

    public QuizResponse addQuiz(QuizRequest quizRequest, String name) {
        Quiz quiz = new Quiz(quizRequest);
        quiz.setOwner(name);
        Quiz savedQuiz = quizRepository.save(quiz);
        return new QuizResponse(savedQuiz);
    }

    public AnswerResponse checkQuizAnswer(long id, AnswerRequest answer, String name) {
        Quiz quiz1 = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Quiz with id %d not found!",id)));
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (answer.getAnswer().equals(quiz1.getAnswer())) {
            UserCompletedQuiz completedQuiz = new UserCompletedQuiz(name, id);
            userCompletedQuizRepository.save(completedQuiz);

            return new AnswerResponse(true);
        }
        return new AnswerResponse(false);
    }

    public void registerUser(UserRegistrationRequest userRequest) {
        String userName = userRequest.getEmail().toLowerCase().trim();
        String password = userRequest.getPassword();

        Optional<User> optionalUser = userRepository.findByUsername(userName);

        if (optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User user = new User(userName, passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void deleteQuiz(long id, String name) {
        Quiz quizToDelete = quizRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "The specified ID doesn't exist"));
        if (!name.equals(quizToDelete.getOwner())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the owner of this quiz!");
        }
        quizRepository.delete(quizToDelete);
    }
}
