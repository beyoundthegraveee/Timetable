package com.source.timetable.services;

import com.source.timetable.models.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject getSubjectById(int subjectId);
}
