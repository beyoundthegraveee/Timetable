package com.source.timetable.services;

import com.source.timetable.DTOs.RequestDTO;
import com.source.timetable.enums.NotificationStatus;
import com.source.timetable.enums.RequestStatus;
import com.source.timetable.models.Request;

import java.util.List;

public interface RequestService{

    List<RequestDTO> findByProfessorId(int professorId);

    Request createRequest(Request request);

    List<Request> findAll();

    List<Request> findByStatusSubmitted();

    Request getById(int id);

    List<Request> findByStatus(RequestStatus requestStatus);
}
