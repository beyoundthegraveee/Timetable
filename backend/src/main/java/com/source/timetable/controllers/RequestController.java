package com.source.timetable.controllers;

import com.source.timetable.DTOs.RequestCreateDTO;
import com.source.timetable.DTOs.RequestDTO;
import com.source.timetable.enums.NotificationStatus;
import com.source.timetable.enums.RequestStatus;
import com.source.timetable.models.Notification;
import com.source.timetable.models.Professor;
import com.source.timetable.models.Request;
import com.source.timetable.models.Subject;
import com.source.timetable.services.LessonService;
import com.source.timetable.services.ProfessorService;
import com.source.timetable.services.RequestService;
import com.source.timetable.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;
    private final ProfessorService professorService;
    private final SubjectService subjectService;
    private final LessonService lessonService;

    @Autowired
    public RequestController(RequestService requestService, ProfessorService professorService, SubjectService subjectService, LessonService lessonService) {
        this.requestService = requestService;
        this.professorService = professorService;
        this.subjectService = subjectService;
        this.lessonService = lessonService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RequestDTO>> getAllRequests() {
        List<Request> requests = requestService.findAll();
        return ResponseEntity.ok(
                requests.stream().map(RequestDTO::fromRequest).toList()
        );
    }

    @GetMapping("/submitted")
    public ResponseEntity<List<RequestDTO>> getSubmittedRequests() {
        List<Request> submitted = requestService.findByStatusSubmitted();
        return ResponseEntity.ok(
                submitted.stream().map(RequestDTO::fromRequest).toList()
        );
    }

    @GetMapping("/professor/{professorId}")
    public List<RequestDTO> getRequestsByProfessor(@PathVariable int professorId) {
        return requestService.findByProfessorId(professorId);
    }

    @PostMapping("/create")
    public ResponseEntity<RequestDTO> createRequest(@RequestBody RequestCreateDTO dto) {
        Professor professor = professorService.getProfessorById(dto.getProfessorId());
        Subject subject = subjectService.getSubjectById(dto.getSubjectId());

        if (professor == null || subject == null) {
            return ResponseEntity.badRequest().build();
        }

        Request request = new Request();
        request.setProfessor(professor);
        request.setSubject(subject);
        request.setClassesDate(dto.getClassesDate());
        request.setClassesTime(dto.getClassesTime());
        request.setDescription(dto.getDescription());

        LocalDateTime classDateTime = LocalDateTime.of(dto.getClassesDate(), dto.getClassesTime());
        if (Duration.between(LocalDateTime.now(), classDateTime).toDays() < 2) {
            request.setRequestStatus(RequestStatus.REJECTED);
        } else {
            request.setRequestStatus(RequestStatus.SUBMITTED);
        }

        Request saved = requestService.createRequest(request);
        return ResponseEntity.ok(RequestDTO.fromRequest(saved));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateRequestStatus(@PathVariable int id, @RequestBody Map<String, String> body) {
        String newStatus = body.get("status");
        Request request = requestService.getById(id);
        if (request == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            request.setRequestStatus(RequestStatus.valueOf(newStatus));
            requestService.createRequest(request);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<RequestDTO>> getAcceptedRequests() {
        List<Request> accepted = requestService.findByStatus(RequestStatus.ACCEPTED);
        return ResponseEntity.ok(
                accepted.stream().map(RequestDTO::fromRequest).toList()
        );
    }

    @PatchMapping("/apply/{requestId}")
    public ResponseEntity<Void> applyRequest(@PathVariable int requestId) {
        Request request = requestService.getById(requestId);
        if (request == null || request.getRequestStatus() != RequestStatus.ACCEPTED) {
            return ResponseEntity.badRequest().build();
        }
        LocalTime startTime = request.getClassesTime();
        LocalTime endTime = startTime.plusMinutes(90);
        boolean success = lessonService.updateLessonTime(
                request.getProfessor().getId(),
                request.getClassesDate(),
                startTime,
                endTime
        );

        if (!success) {
            return ResponseEntity.status(409).build();
        }
        requestService.createRequest(request);
        return ResponseEntity.ok().build();
    }


}