package com.immunizationtracker.immunization.repositories;

import com.immunizationtracker.immunization.models.Doctor;
import com.immunizationtracker.immunization.models.Guardian;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface DoctorRepository extends CrudRepository<Doctor, Long>
{
    @Transactional
    @Modifying
    @Query(value = "DELETE from permissions where guardianid = :guardianid")
    void deletePermissionsByGuardianId( long guardianid);


}
