package webdev.models.exam.joined;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_MULTI_CHOICE_QUESTION")
public class MultipleChoiceQuestionJoined extends BaseQuestionJoined {

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    @Column(name = "CHOICE", nullable = false)
    private String choice;
}
