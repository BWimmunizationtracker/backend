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

    void updateGuardians(long guardianid, String firstname, String lastname, long userid);

    void putUserToGuardian(long guardianid, long userid);


}
