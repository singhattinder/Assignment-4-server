package webdev.repositories;

import org.springframework.data.repository.CrudRepository;
import webdev.models.exam.joined.MultipleChoiceQuestionJoined;

public interface MultipleChoiceQuestionRepositoryJoined extends CrudRepository<MultipleChoiceQuestionJoined, Integer> {
}
