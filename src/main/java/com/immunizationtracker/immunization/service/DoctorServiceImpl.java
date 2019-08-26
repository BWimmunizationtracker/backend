package com.immunizationtracker.immunization.service;

import com.immunizationtracker.immunization.models.Doctor;
import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.models.Permission;
import com.immunizationtracker.immunization.models.Ward;
import com.immunizationtracker.immunization.repositories.DoctorRepository;
import com.immunizationtracker.immunization.repositories.GuardianRepository;
import com.immunizationtracker.immunization.repositories.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "doctorService")
public class DoctorServiceImpl implements DoctorService
{
    // We instantiate an instance of the DoctorRepository here
    // so that we have access to the data access object methods
    @Autowired
private DoctorRepository doctorRepository;

    @Autowired
    private GuardianRepository guardianRepository;




    // next we will implement the methods from our interface
    @Override
    public List<Doctor> findAll()
    {
        List<Doctor> list = new ArrayList();
        doctorRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Doctor findDoctorById(long id)
    {
        return doctorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (doctorRepository.findById(id).isPresent())
        {
            doctorRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }


    }

    @Transactional
    @Override
    public Doctor save(Doctor doctor)
    {
        Doctor newDoctor = new Doctor();

        newDoctor.setName(doctor.getName());


        return doctorRepository.save(newDoctor);
    }

    @Transactional
    @Override
    public Doctor update(Doctor doctor, long id)
    {
        Doctor currentDoctor = doctorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        Guardian currentGuardian = guardianRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (doctor.getPermissions().size() > 0)
        {
            for (Permission permission : doctor.getPermissions())
            {
                currentDoctor.getPermissions().add(new Permission(permission.getGuardian(), currentDoctor));
            }
            return doctorRepository.save(currentDoctor);
        }
        throw new EntityNotFoundException(doctor.getName());

    }

    public void updatePermissions(long doctorid, long guardianid)
    {
        Doctor currentDoctor = doctorRepository.findById(doctorid).orElseThrow(() -> new EntityNotFoundException(Long.toString(doctorid)));
        Guardian currentGuardian = guardianRepository.findById(guardianid).orElseThrow(() -> new EntityNotFoundException(Long.toString(guardianid)));

        doctorRepository.insertPermission(guardianid, doctorid);

    }



}
