package com.source.timetable.services;

import com.source.timetable.models.Lesson;
import com.source.timetable.repositories.LessonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
}
