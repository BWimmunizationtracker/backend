package com.immunizationtracker.immunization.repositories;

import com.immunizationtracker.immunization.models.Doctor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface DoctorRepository extends CrudRepository<Doctor, Long>
{
//    @Transactional
//    @Modifying
//    @Query(value = "DELETE FROM permissions WHERE guardianid = :guardianid")
//    void deletePermissionsByGuardianId(long guardianid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO permissions(guardianid, doctorid) values(:guardianid, :doctorid)", nativeQuery = true)
    void insertPermission(long guardianid, long doctorid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE doctors SET userid = :userid WHERE doctorid = :doctorid", nativeQuery = true)
    void putUserToDoctor(long doctorid, long userid);

//    @Transactional
//    @Modifying
//    @Query(value = "INSERT INTO userroles(guardianid, doctorid) values(:guardianid, :doctorid)", nativeQuery = true)
//    void insertPermission(long guardianid, long doctorid);


}
