package engine.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import engine.model.UserCompletedQuiz;

import java.time.LocalDateTime;

public class CompletedQuizResponse {
    private Long id;
    @JsonProperty("completedAt")
    private LocalDateTime completedAt;

    public CompletedQuizResponse(UserCompletedQuiz completedQuiz) {
        this.id = completedQuiz.getQuizId();
        this.completedAt = completedQuiz.getCompletedAt();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
