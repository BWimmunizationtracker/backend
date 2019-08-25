package com.immunizationtracker.immunization.repositories;

import com.immunizationtracker.immunization.models.Guardian;
import org.springframework.data.repository.CrudRepository;

public interface GuardianRepository extends CrudRepository<Guardian, Long>
{

}
