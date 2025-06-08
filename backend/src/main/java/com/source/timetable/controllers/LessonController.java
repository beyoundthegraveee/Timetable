package com.source.timetable.controllers;

import com.source.timetable.DTOs.LessonEvent;
import com.source.timetable.models.Lesson;
import com.source.timetable.services.LessonService;
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

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
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

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
