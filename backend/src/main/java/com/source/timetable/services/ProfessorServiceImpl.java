package com.source.timetable.services;

import com.source.timetable.models.Professor;
import com.source.timetable.repositories.ProfessorRepo;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepo professorRepo;

    public ProfessorServiceImpl(ProfessorRepo professorRepo) {
        this.professorRepo = professorRepo;
    }


    @Override
    public Professor getProfessorById(int professorId) {
        return professorRepo.findById(professorId).get();
    }
}
