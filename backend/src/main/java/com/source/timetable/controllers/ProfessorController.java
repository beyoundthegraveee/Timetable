package com.source.timetable.controllers;

import com.source.timetable.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(final ProfessorService professorService) {
        this.professorService = professorService;
    }
}
