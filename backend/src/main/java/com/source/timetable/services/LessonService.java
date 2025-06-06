package com.source.timetable.services;

import com.source.timetable.models.Lesson;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


public interface LessonService {

    List<Lesson> findByGroupId(int groupId);
}
