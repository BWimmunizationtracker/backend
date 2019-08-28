package com.immunizationtracker.immunization.service;

import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.models.Ward;

import java.util.List;

public interface WardService
{
    List<Ward> findAll();

    Ward findWardById(long id);

    void delete(long id);

    Ward save(Ward ward);

    Ward update(Ward ward, long id);

    void putWardToImmunization(long immunizationid, long wardid);

    void putGuardianToWard(long wardid, long guardianid);
}
