package io.jeremy.engine.dtos.responses;

public class AnswerResponse {
    private boolean success;
    private String feedback;

    public AnswerResponse(boolean success) {
        this.success = success;
        if (success) {
            this.feedback = "Congratulations, you're right!";
        } else {
            this.feedback = "Wrong answer! Please, try again.";
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
