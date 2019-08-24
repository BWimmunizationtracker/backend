package com.immunizationtracker.immunization.repositories;

import com.immunizationtracker.immunization.models.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long>
{
}
