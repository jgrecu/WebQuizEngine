package engine.services;

import engine.dtos.requests.AnswerRequest;
import engine.dtos.requests.QuizRequest;
import engine.dtos.responses.AnswerResponse;
import engine.dtos.responses.QuizResponse;
import engine.model.Quiz;
import engine.repositories.QuizRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
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

    public QuizResponse addQuiz(QuizRequest quizRequest) {
        Quiz quiz = new Quiz(quizRequest);
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
}
