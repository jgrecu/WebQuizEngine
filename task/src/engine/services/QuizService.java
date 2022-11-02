package engine.services;

import engine.dtos.requests.AnswerRequest;
import engine.dtos.requests.QuizRequest;
import engine.dtos.requests.UserRegistrationRequest;
import engine.dtos.responses.AnswerResponse;
import engine.dtos.responses.QuizResponse;
import engine.model.Quiz;
import engine.model.User;
import engine.repositories.QuizRepository;
import engine.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public QuizService(QuizRepository quizRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public QuizResponse getQuizById(long id) {
        return quizRepository.findById(id)
                .map(QuizResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Quiz with id %d not found!".formatted(id)));
    }

    public List<QuizResponse> getAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(QuizResponse::new)
                .collect(Collectors.toList());
    }

    public QuizResponse addQuiz(QuizRequest quizRequest, String name) {
        Quiz quiz = new Quiz(quizRequest);
        quiz.setOwner(name);
        Quiz savedQuiz = quizRepository.save(quiz);
        return new QuizResponse(savedQuiz);
    }

    public AnswerResponse checkQuizAnswer(long id, AnswerRequest answer) {
        Quiz quiz1 = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Quiz with id %d not found!".formatted(id)));

        if (answer.getAnswer().equals(quiz1.getAnswer())) {
            return new AnswerResponse(true);
        }
        return new AnswerResponse(false);
    }

    public void registerUser(UserRegistrationRequest userRequest) {
        String userName = userRequest.getEmail().toLowerCase().trim();
        String password = userRequest.getPassword();

        Optional<User> optionalUser = userRepository.findByUsername(userName);

        if (optionalUser.isPresent()) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
