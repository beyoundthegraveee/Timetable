package com.source.timetable.services;

import com.source.timetable.models.Lesson;
import com.source.timetable.repositories.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepo lessonRepo;

    @Autowired
    public LessonServiceImpl(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }


    @Override
    public List<Lesson> findByGroupId(int groupId) {
        return lessonRepo.findByGroupId(groupId);
    }

    @Override
    public List<Lesson> findByProfessorId(int professorId) {
        return lessonRepo.findByProfessorId(professorId);
    }

    @Override
    public boolean updateLessonTime(int professorId, LocalDate date, LocalTime start, LocalTime end) {
        List<Lesson> lessons = lessonRepo.findByProfessorIdAndDateOfClass(professorId, date);
        if (lessons.isEmpty()) {
            System.out.printf("No lessons found for professorId=%d on date=%s%n", professorId, date);
            return false;
        }
        Lesson lesson = lessons.get(0);
        lesson.setStartTime(start);
        lesson.setEndTime(end);
        lessonRepo.save(lesson);
        return true;
    }
}
