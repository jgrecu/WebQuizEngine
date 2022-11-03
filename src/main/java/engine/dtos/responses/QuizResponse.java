package engine.dtos.responses;

import engine.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizResponse {
    private long id;
    private String title;
    private String text;
    private List<String> options;

    public QuizResponse() {
        this.options = new ArrayList<>();
    }

    public QuizResponse(Quiz quiz) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.text = quiz.getText();
        this.options = quiz.getOptions();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
