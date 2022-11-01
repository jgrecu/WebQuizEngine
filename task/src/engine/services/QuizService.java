package engine.services;

import engine.dtos.requests.QuizRequest;
import engine.dtos.responses.AnswerResponse;
import engine.dtos.responses.QuizResponse;
import engine.model.Quiz;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private AtomicInteger id = new AtomicInteger(1);
    private final List<Quiz> quizzes = new ArrayList<>();


    public QuizResponse getQuizById(int id) {
        return quizzes.stream()
                .filter(quiz -> quiz.getId() == id)
                .findFirst()
                .map(QuizResponse::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Quiz with id %d not found!".formatted(id)));
    }

    public List<QuizResponse> getAllQuizzes() {
        return quizzes.stream().map(QuizResponse::new).collect(Collectors.toList());
    }

    public QuizResponse addQuiz(QuizRequest quizRequest) {
        Quiz quiz = new Quiz(quizRequest);
        quiz.setId(id.getAndIncrement());
        quizzes.add(quiz);
        return new QuizResponse(quiz);
    }
    public AnswerResponse checkQuizAnswer(int id, int answer) {
        Quiz quiz1 = quizzes.stream()
                .filter(quiz -> quiz.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Quiz with id %d not found!".formatted(id)));
        if (answer == quiz1.getAnswer()) {
            return new AnswerResponse(true);
        }
        return new AnswerResponse(false);
    }
}
