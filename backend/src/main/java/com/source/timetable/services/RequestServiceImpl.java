package com.source.timetable.services;

import com.source.timetable.DTOs.RequestDTO;
import com.source.timetable.enums.RequestStatus;
import com.source.timetable.models.Professor;
import com.source.timetable.models.Request;
import com.source.timetable.repositories.RequestRepo;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepo requestRepo;

    public RequestServiceImpl(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }


    public List<RequestDTO> findByProfessorId(int professorId) {
        return requestRepo.findByProfessorId(professorId).stream()
                .map(RequestDTO::fromRequest)
                .toList();
    }

    @Override
    public Request createRequest(Request request) {
        LocalDateTime requestedDateTime = LocalDateTime.of(request.getClassesDate(), request.getClassesTime());
        if (Duration.between(LocalDateTime.now(), requestedDateTime).toDays() < 2) {
            request.setRequestStatus(RequestStatus.REJECTED);
        } else {
            request.setRequestStatus(RequestStatus.SUBMITTED);
        }
        return requestRepo.save(request);
    }

    @Override
    public Request updateRequest(Request request) {
        return requestRepo.save(request);
    }

    @Override
    public List<Request> findAll() {
        return requestRepo.findAll();
    }

    @Override
    public List<Request> findByStatusSubmitted() {
        return requestRepo.findByRequestStatus(RequestStatus.SUBMITTED);
    }

    @Override
    public Request getById(int id) {
        return requestRepo.findById(id).orElse(null);
    }

    @Override
    public List<Request> findByStatus(RequestStatus requestStatus) {
        return requestRepo.findByRequestStatus(requestStatus);
    }

    @Override
    public Request findByProfessorDateAndTime(Professor professor, LocalDate date, LocalTime startTime) {
        return requestRepo.findByProfessorAndClassesDateAndClassesTime(professor, date, startTime);
    }


}
