package webdev.models.exam.joined;

import com.fasterxml.jackson.annotation.JsonIgnore;
import webdev.models.Exam;

import javax.persistence.*;

@Entity
@Table(name = "JOINED_BASE_QUESTION")
@Inheritance(strategy=InheritanceType.JOINED)
public class BaseQuestionJoined {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int points;
	private String title;
	private String type;
	private String description;
	private String instructions;


	@ManyToOne
	@JsonIgnore
	private Exam exam;



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
}
