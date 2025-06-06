package com.source.timetable.services;

import com.source.timetable.models.GroupOfStudents;

import java.util.List;

public interface GroupService {

    List<GroupOfStudents> getAllGroups();

    GroupOfStudents getGroupByName(String name);

}
