package webdev.repositories;

import org.springframework.data.repository.CrudRepository;
import webdev.models.exam.joined.EssayQuestionJoined;

public interface EssayQuestionRepository extends CrudRepository<EssayQuestionJoined, Integer > {
}
