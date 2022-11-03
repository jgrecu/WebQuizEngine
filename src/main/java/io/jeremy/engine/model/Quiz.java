package io.jeremy.engine.model;

import io.jeremy.engine.dtos.requests.QuizRequest;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String text;
    @ElementCollection
    private List<String> options;
    @ElementCollection
    private List<Integer> answer;
    private String owner;

    public Quiz() {
    }

    public Quiz(QuizRequest request) {
        this.title = request.getTitle();
        this.text = request.getText();
        this.options = request.getOptions();
        this.answer = request.getAnswer();
        this.owner = "";
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

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
