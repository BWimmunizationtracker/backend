package com.immunizationtracker.immunization.service;

import com.immunizationtracker.immunization.models.Doctor;
import com.immunizationtracker.immunization.models.Ward;

import java.util.List;

public interface DoctorService
{
    List<Doctor> findAll();

    Doctor findDoctorById(long id);

    void delete(long id);

    Doctor save(Doctor doctor);

    Doctor update(Doctor doctor, long id);

    void updatePermissions(long doctorid, long guardianid);
}
