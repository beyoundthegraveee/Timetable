package com.source.timetable.services;

import com.source.timetable.models.Lesson;
import com.source.timetable.models.Professor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;


public interface LessonService {

    List<Lesson> findByGroupId(int groupId);

    List<Lesson> findByProfessorId(int professorId);

    boolean updateLessonTime(int professorId, LocalDate date, LocalTime start, LocalTime end);
}
