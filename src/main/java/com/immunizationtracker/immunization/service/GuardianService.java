package com.immunizationtracker.immunization.service;

import com.immunizationtracker.immunization.models.Guardian;

import java.util.List;

public interface GuardianService
{
    List<Guardian> findAll();

    Guardian findGuardianById(long id);

    void delete(long id);

    Guardian save(Guardian guardian);

    Guardian update(Guardian guardian, long id);

    Guardian updateGuardianToAuthor(long guardianid, long doctorid);

}
