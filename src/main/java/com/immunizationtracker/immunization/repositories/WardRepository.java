package com.immunizationtracker.immunization.repositories;

import com.immunizationtracker.immunization.models.Ward;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface WardRepository extends CrudRepository<Ward, Long>
{

    @Transactional
    @Modifying
    @Query(value = "UPDATE immunizations SET wardid = :wardid WHERE immunizationid = :immunizationid", nativeQuery = true)
    void putWardToImmunization(long immunizationid, long wardid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE wards SET guardianid = :guardianid WHERE wardid = :wardid", nativeQuery = true)
    void putGuardianToWard(long wardid, long guardianid);


}
