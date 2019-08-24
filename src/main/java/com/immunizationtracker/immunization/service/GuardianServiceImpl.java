package com.immunizationtracker.immunization.service;

import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.repositories.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void delete(long id)
    {

    }

    @Override
    public Guardian save(Guardian guardian)
    {
        return null;
    }

    @Override
    public Guardian update(Guardian guardian, long id)
    {
        return null;
    }
}
