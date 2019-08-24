package com.immunizationtracker.immunization.repositories;

import com.immunizationtracker.immunization.models.Ward;
import org.springframework.data.repository.CrudRepository;

public interface WardRepository extends CrudRepository<Ward, Long>
{
}
