package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webdev.models.Assignment;
import webdev.models.Exam;
import webdev.models.Lesson;
import webdev.models.Module;
import webdev.models.Widget;
import webdev.models.exam.joined.BaseQuestionJoined;
import webdev.models.exam.joined.TrueOrFalseQuestionJoined;
import webdev.repositories.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService  {


    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    WidgetRepository widgetRepository;






    @GetMapping("/api/assignment/{aId}")
    public Assignment findAllAssignmentById(@PathVariable("aId") int aId) {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(aId);
        if(optionalAssignment.isPresent()) {
            Assignment assignment = optionalAssignment.get();
            return assignment;
        }
        return null;
    }

    @GetMapping("/api/lesson/{lessonId}/assignment")
    public List<Assignment> findAllAssignmentForLesson(@PathVariable("lessonId") int lessonId) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if(optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            List<Widget> widgets = lesson.getWidgets();
            List<Assignment> assignments=null;
            for(Widget w: widgets){
                if (w.getWidgetType().equals("Exam")){
                    Assignment assignment = (Assignment) w;
                    assignments.add(assignment);

                }
                return assignments;
            }
        }
        return null;
    }

    @DeleteMapping("/api/assignment/{aId}")
    public void deleteCourse(@PathVariable("aId") int id) {
        assignmentRepository.deleteById(id);
    }



    @PostMapping("/api/lesson/{lessonId}/assignment")
    public void createAssignmentForLesson(@PathVariable("lessonId") int lessonId,
                                          @RequestBody Widget widget) {

        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
        if(optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            if (widget.getWidgetType().equals("Assignment")){
                Widget widget1 = new Widget();
                widget1 = (Widget) widget;
                widget1.setLesson(lesson);
                widgetRepository.save(widget1);
            }

        }

    }


    @GetMapping("/api/assignment")
    public Iterable<Assignment> findAllAssignments() {
        return assignmentRepository.findAll();
    }



}
