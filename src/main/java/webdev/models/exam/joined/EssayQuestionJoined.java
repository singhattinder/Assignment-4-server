package webdev.models.exam.joined;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_ESSAY_QUESTION")
public class EssayQuestionJoined extends BaseQuestionJoined {

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Column(name = "ESSAY_ANSWER", nullable = false)
    private String  answer;
}
