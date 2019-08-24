package com.immunizationtracker.immunization.repositories;

import com.immunizationtracker.immunization.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
