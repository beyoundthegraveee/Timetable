package com.source.timetable.repositories;

import com.source.timetable.enums.RequestStatus;
import com.source.timetable.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<Request,Integer> {

    List<Request> findByProfessorId(int professorId);

    List<Request> findByRequestStatus (RequestStatus requestStatus);
}
