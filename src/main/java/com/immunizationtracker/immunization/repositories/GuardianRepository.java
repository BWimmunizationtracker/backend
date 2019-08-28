package com.immunizationtracker.immunization.repositories;

import com.immunizationtracker.immunization.models.Guardian;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface GuardianRepository extends CrudRepository<Guardian, Long>
{
    // insert into a Guardian the userid of a user to be associated with them


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO guardians(guardianid, firstname, lastname, userid) VALUES (:guardianid, :firstname, :lastname, :userid)", nativeQuery = true)
    void insertGuardian(long guardianid, String firstname, String lastname, long userid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE guardians SET userid = :userid WHERE guardianid = :guardianid", nativeQuery = true)
    void putUserToGuardian(long guardianid, long userid);
}

//    INSERT INTO guardians(guardianid, firstname, lastname, userid) VALUES (:guardianid, :firstname, :lastname, :userid)

//    UPDATE guardians SET userid = 30 WHERE guardianid = 1