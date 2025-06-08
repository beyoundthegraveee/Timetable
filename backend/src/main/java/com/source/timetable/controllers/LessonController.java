package com.source.timetable.controllers;

import com.source.timetable.DTOs.LessonEvent;
import com.source.timetable.enums.RequestStatus;
import com.source.timetable.models.Lesson;
import com.source.timetable.models.Professor;
import com.source.timetable.models.Request;
import com.source.timetable.services.LessonService;
import com.source.timetable.services.ProfessorService;
import com.source.timetable.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    private final RequestService requestService;

    private final ProfessorService professorService;

    @Autowired
    public LessonController(LessonService lessonService, RequestService requestService, ProfessorService professorService) {
        this.lessonService = lessonService;
        this.requestService = requestService;
        this.professorService = professorService;
    }

    @GetMapping("/group/{groupId}")
    public List<LessonEvent> getLessonsForGroup(@PathVariable int groupId) {
        return lessonService.findByGroupId(groupId).stream()
                .map(LessonEvent::fromLesson)
                .toList();
    }

    @GetMapping("/professor/{professorId}")
    public List<LessonEvent> getLessonsByProfessor(@PathVariable int professorId) {
        List<Lesson> lessons = lessonService.findByProfessorId(professorId);
        return lessons.stream()
                .map(LessonEvent::fromLesson)
                .collect(Collectors.toList());
    }

    @PostMapping("/update-time")
    public ResponseEntity<Void> updateLessonTime(@RequestBody Map<String, String> body) {
        try {
            int professorId = Integer.parseInt(body.get("professorId"));
            LocalDate date = LocalDate.parse(body.get("date"));
            LocalTime startTime = LocalTime.parse(body.get("startTime"));
            LocalTime endTime = LocalTime.parse(body.get("endTime"));
            boolean success = lessonService.updateLessonTime(professorId, date, startTime, endTime);
            if (!success) {
                return ResponseEntity.status(404).build();
            }

            Professor professor = professorService.getProfessorById(professorId);
            Request request = requestService.findByProfessorDateAndTime(professor, date, startTime);
            if (request != null) {
                request.setRequestStatus(RequestStatus.IMPLEMENTED);
                requestService.updateRequest(request);
            }

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
