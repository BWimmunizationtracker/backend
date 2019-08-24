package com.immunizationtracker.immunization.repositories;

import com.immunizationtracker.immunization.models.Immunization;
import org.springframework.data.repository.CrudRepository;

public interface ImmunizationRepository extends CrudRepository<Immunization, Long>
{
}
