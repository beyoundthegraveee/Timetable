package com.source.timetable.controllers;

import com.source.timetable.DTOs.LessonEvent;
import com.source.timetable.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
