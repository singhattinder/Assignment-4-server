package webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webdev.models.Assignment;
import webdev.models.Lesson;
import webdev.models.Widget;
import webdev.models.exam.joined.*;
import webdev.repositories.*;
import webdev.models.Exam;
import webdev.models.exam.joined.*;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	TrueOrFalseQuestionRepositoryJoined trueFalseRepository;
	@Autowired
	BaseQuestionRepositoryJoined baseQuestionRepositoryJoined;
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	LessonRepository lessonRepository;


	@GetMapping("/api/exam/{eId}/truefalse")
	public TrueOrFalseQuestionJoined findTrueFalseQuestionById(@PathVariable("eId") int questionId) {
		Optional<TrueOrFalseQuestionJoined> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@PostMapping("/api/exam/{eId}/truefalse")
	public void createTrueFalseQuestionById(@PathVariable("eId") int eid,
	@RequestBody TrueOrFalseQuestionJoined trueOrFalseQuestionJoined) {
		Optional<Exam> optional = examRepository.findById(eid);
		if(optional.isPresent()) {

			Exam exam = optional.get();
			BaseQuestionJoined baseQuestionJoined =  trueOrFalseQuestionJoined;
			List<BaseQuestionJoined> baseQuestionJoined1 =null;
			baseQuestionJoined1.add(baseQuestionJoined);
			exam.setQuestions(baseQuestionJoined1);

		}
	}
	@PostMapping("/api/exam/{eId}/essay")
	public void createEssayQuestionById(@PathVariable("eId") int eid,
											@RequestBody EssayQuestionJoined essayQuestionJoined) {
		Optional<Exam> optional = examRepository.findById(eid);
		if(optional.isPresent()) {

			Exam exam = optional.get();
			BaseQuestionJoined baseQuestionJoined = essayQuestionJoined;
			List<BaseQuestionJoined> baseQuestionJoined1 =null;
			baseQuestionJoined1.add(baseQuestionJoined);
			exam.setQuestions(baseQuestionJoined1);

		}
	}
	@PostMapping("/api/exam/{eId}/choice")
	public void createExamQuestionById(@PathVariable("eId") int eid,
											@RequestBody MultipleChoiceQuestionJoined multipleChoiceQuestionJoined) {
		Optional<Exam> optional = examRepository.findById(eid);
		if(optional.isPresent()) {

			Exam exam = optional.get();
			BaseQuestionJoined baseQuestionJoined =  multipleChoiceQuestionJoined;
			List<BaseQuestionJoined> baseQuestionJoined1 =null;
			baseQuestionJoined1.add(baseQuestionJoined);
			exam.setQuestions(baseQuestionJoined1);

		}
	}

	@PostMapping("/api/exam/{eId}/blanks")
	public void createFillInTheBlanksQuestionById(@PathVariable("eId") int eid,
											@RequestBody FillInTheBlankQuestionJoined fillInTheBlankQuestionJoined) {
		Optional<Exam> optional = examRepository.findById(eid);
		if(optional.isPresent()) {

			Exam exam = optional.get();
			BaseQuestionJoined baseQuestionJoined =  fillInTheBlankQuestionJoined;
			List<BaseQuestionJoined> baseQuestionJoined1 =null;
			baseQuestionJoined1.add(baseQuestionJoined);
			exam.setQuestions(baseQuestionJoined1);

		}
	}
	
	@GetMapping("/api/exam/{eId}")
	public List<BaseQuestionJoined> findAllQuestionsForExam(@PathVariable("eId") int eId) {
		Optional<Exam> optionalExam = examRepository.findById(eId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<BaseQuestionJoined> questions = exam.getQuestions();
			int count = questions.size();
			return questions;
		}
		return null;
	}

	@GetMapping("/api/lesson/{lessonId}/exam")
	public List<Exam> findAllAssignmentForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if(optionalLesson.isPresent()) {
			Lesson lesson = optionalLesson.get();
			List<Widget> widgets = lesson.getWidgets();
			List<Exam> exams=null;
			for(Widget w: widgets){
				if (w.getWidgetType().equals("Exam")){
					Exam exam = (Exam) w;
					exams.add(exam);

				}
				return exams;
			}
		}
		return null;
	}



	@PostMapping("/api/lesson/{lessonId}/exam")
	public void createAssignmentForLesson(@PathVariable("lessonId") int lessonId,
										  @RequestBody Widget widget) {

		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if(optionalLesson.isPresent()) {
			Lesson lesson = optionalLesson.get();
			if (widget.getWidgetType().equals("Exam")){
				Widget widget1 = new Widget();
				widget1 = (Widget) widget;
				widget1.setLesson(lesson);
				widgetRepository.save(widget1);
			}

		}

	}

	@DeleteMapping("/api/exam/{eId}")
	public void deleteCourse(@PathVariable("eId") int id) {
		examRepository.deleteById(id);
	}



	@GetMapping("/api/exam")
	public Iterable<Exam> findAllCourses() {
		return examRepository.findAll();
	}




}
