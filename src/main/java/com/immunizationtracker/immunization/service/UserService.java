package com.immunizationtracker.immunization.service;

import com.immunizationtracker.immunization.models.User;

import java.util.List;

public interface UserService
{

    List<User> findAll();

    User findUserById(long id);

    void delete(long id);

    User save(User user);

    User saveDoctor(User user);

    User saveGuardian(User user);

    User update(User user, long id);

    User findUserByUsername(String username);
}
