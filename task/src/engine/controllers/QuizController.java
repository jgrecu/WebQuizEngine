package engine.controllers;

import engine.dtos.requests.QuizRequest;
import engine.dtos.responses.AnswerResponse;
import engine.dtos.responses.QuizResponse;
import engine.services.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quizzes/{id}")
    public QuizResponse getQuiz(@PathVariable int id) {
        return quizService.getQuizById(id);
    }

    @GetMapping("/quizzes")
    public List<QuizResponse> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping("/quizzes")
    public QuizResponse addQuiz(@RequestBody QuizRequest request) {
        return quizService.addQuiz(request);
    }

    @PostMapping("/quizzes/{id}/solve")
    public AnswerResponse answerQuiz(@RequestParam(name = "answer") int answer, @PathVariable int id) {
        return quizService.checkQuizAnswer(id, answer);
    }
}
