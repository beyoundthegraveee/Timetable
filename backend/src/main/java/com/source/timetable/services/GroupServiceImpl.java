package com.source.timetable.services;

import com.source.timetable.models.GroupOfStudents;
import com.source.timetable.repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepo groupRepo;

    @Autowired
    public GroupServiceImpl(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    @Override
    public List<GroupOfStudents> getAllGroups() {
        return groupRepo.findAll();
    }

    @Override
    public GroupOfStudents getGroupByName(String name) {
        return groupRepo.findByName(name);
    }

    @Override
    public GroupOfStudents getById(int toGroupId) {
        return groupRepo.findById(toGroupId).orElse(null);
    }
}
