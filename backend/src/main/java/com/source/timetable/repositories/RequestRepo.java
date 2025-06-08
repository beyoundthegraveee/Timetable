package com.source.timetable.repositories;

import com.source.timetable.enums.RequestStatus;
import com.source.timetable.models.Professor;
import com.source.timetable.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request,Integer> {

    List<Request> findByProfessorId(int professorId);

    List<Request> findByRequestStatus (RequestStatus requestStatus);

    Request findByProfessorAndClassesDateAndClassesTime(Professor professor, LocalDate date, LocalTime startTime);
}
