package com.source.timetable.services;


import com.source.timetable.models.User;

import java.util.Optional;

public interface UserService {

    Iterable<User> getAllUsers();

    Optional<User> authenticate(String login, String password);


}
