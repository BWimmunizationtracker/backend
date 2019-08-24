package com.immunizationtracker.immunization.service;

import com.immunizationtracker.immunization.models.Immunization;
import com.immunizationtracker.immunization.models.Ward;

import java.util.List;

public interface ImmunizationService
{

    List<Immunization> findAll();

    Immunization findImmunizationById(long id);

    void delete(long id);

    Immunization save(Immunization immunization);

    Immunization update(Immunization immunization, long id);
}
