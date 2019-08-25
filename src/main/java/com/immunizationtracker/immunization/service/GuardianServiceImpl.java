package com.immunizationtracker.immunization.service;

import com.immunizationtracker.immunization.models.Doctor;
import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.models.Permission;
import com.immunizationtracker.immunization.repositories.DoctorRepository;
import com.immunizationtracker.immunization.repositories.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "guardianService")
public class GuardianServiceImpl implements GuardianService
{
    // We instantiate an instance of the GuardianRepository here
    // so that we have access to the data access object methods
    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    // next we will implement the methods from our interface
    @Override
    public List<Guardian> findAll()
    {
        List<Guardian> list = new ArrayList();
        guardianRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Guardian findGuardianById(long id)
    {
        return guardianRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (guardianRepository.findById(id).isPresent())
        {
            guardianRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }

    @Transactional
    @Modifying
    @Override
    public Guardian save(Guardian guardian)
    {
        Guardian newGuardian = new Guardian();

        newGuardian.setFirstname(guardian.getFirstname());
        newGuardian.setLastname(guardian.getLastname());
        ArrayList<Permission> newPermissions = new ArrayList<>();

        for (Permission p : guardian.getPermissions())
        {
            newPermissions.add(new Permission(newGuardian, p.getDoctor()));
        }
        newGuardian.setPermissions(newPermissions);
        return guardianRepository.save(newGuardian);
    }

    @Transactional
    @Modifying
    @Override
    public Guardian update(Guardian guardian, long id)
    {
      Guardian currentGuardian = guardianRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

      if (guardian.getFirstname() != null)
      {
          currentGuardian.setFirstname(guardian.getFirstname());
      }
      if (guardian.getLastname() != null)
      {
            currentGuardian.setLastname(guardian.getLastname());
      }
      if (guardian.getPermissions().size() > 0)
      {
          doctorRepository.deletePermissionsByGuardianId(currentGuardian.getParentid());

          for (Permission p : guardian.getPermissions())
          {
              doctorRepository.insertPermission(id, p.getDoctor().getDoctorid());
          }
      }

      return guardianRepository.save(currentGuardian);

    }


}
