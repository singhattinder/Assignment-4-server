package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.exam.joined.TrueOrFalseQuestionJoined;
import webdev.models.exam.joined.BaseQuestionJoined;
import webdev.repositories.TrueOrFalseQuestionRepositoryJoined;
import webdev.repositories.FillInTheBlankQuestionRepositoryJoined;
import webdev.repositories.ExamRepository;
import webdev.models.Exam;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueOrFalseQuestionRepositoryJoined trueFalseRepository;


	@GetMapping("/api/truefalse/{questionId}")
	public TrueOrFalseQuestionJoined findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueOrFalseQuestionJoined> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{examId}/question")
	public List<BaseQuestionJoined> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseQuestionJoined> questions = exam.getQuestions();
			int count = questions.size();
			return questions;
		}
		return null;
	}

}
