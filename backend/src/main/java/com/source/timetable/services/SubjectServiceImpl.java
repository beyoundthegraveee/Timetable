package com.source.timetable.services;

import com.source.timetable.models.Subject;
import com.source.timetable.repositories.SubjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepo subjectRepo;

    public SubjectServiceImpl(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }


    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepo.findAll();
    }

    @Override
    public Subject getSubjectById(int subjectId) {
        return subjectRepo.findById(subjectId).get();
    }
}
