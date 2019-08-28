package com.immunizationtracker.immunization.service;

import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.models.Immunization;
import com.immunizationtracker.immunization.models.Ward;
import com.immunizationtracker.immunization.repositories.GuardianRepository;
import com.immunizationtracker.immunization.repositories.ImmunizationRepository;
import com.immunizationtracker.immunization.repositories.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "wardService")
public class WardServiceImpl implements WardService
{
    // We instantiate an instance of the WardRepository here
    // so that we have access to the data access object methods
    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private ImmunizationRepository immunizationRepository;


    // next we will implement the methods from our interface
    @Override
    public List<Ward> findAll()
    {
        List<Ward> list = new ArrayList();
        wardRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Ward findWardById(long id)
    {
        return wardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) throws EntityNotFoundException
    {
        if (wardRepository.findById(id).isPresent())
        {
            wardRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }

    @Transactional
    @Override
    public Ward save(Ward ward)
    {
        Ward newWard = new Ward();

        newWard.setFirstname(ward.getFirstname());
        newWard.setLastname(ward.getLastname());

        return wardRepository.save(newWard);
    }

    @Override
    public Ward update(Ward ward, long id)
    {
        Ward currentWard = wardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (ward.getFirstname() != null)
        {
            currentWard.setFirstname(ward.getFirstname());
            currentWard.setLastname(ward.getLastname());
        }
        return wardRepository.save(currentWard);

    }

    public void putWardToImmunization(long immunizationid, long wardid)
    {
        Immunization currentImmunization = immunizationRepository.findById(immunizationid).orElseThrow(() -> new EntityNotFoundException(Long.toString(immunizationid)));
        wardRepository.putWardToImmunization(immunizationid, wardid);
    }

    public void putGuardianToWard(long wardid, long guardianid)
    {
        Ward currentward = wardRepository.findById(guardianid).orElseThrow(() -> new EntityNotFoundException(Long.toString(wardid)));
        wardRepository.putGuardianToWard(wardid, guardianid);
    }

}

