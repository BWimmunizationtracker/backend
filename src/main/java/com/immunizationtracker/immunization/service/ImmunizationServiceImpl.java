package com.immunizationtracker.immunization.service;

import com.immunizationtracker.immunization.exceptions.ResourceNotFoundException;
import com.immunizationtracker.immunization.models.Immunization;
import com.immunizationtracker.immunization.repositories.ImmunizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service(value = "immunizationService")
public class ImmunizationServiceImpl implements ImmunizationService
{
    // We instantiate an instance of the WardRepository here
    // so that we have access to the data access object methods
    @Autowired
    private ImmunizationRepository immunizationRepository;

    // next we will implement the methods from our interface
    @Override
    public List<Immunization> findAll()
    {
        List<Immunization> list = new ArrayList();
        immunizationRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Immunization findImmunizationById(long id)
    {
        return immunizationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) throws ResourceNotFoundException
    {
        if (immunizationRepository.findById(id).isPresent())
        {
            immunizationRepository.deleteById(id);
        }
        else
        {
            throw new ResourceNotFoundException(Long.toString(id));
        }

    }

    @Transactional
    @Override
    public Immunization save(Immunization immunization)
    {
        Immunization newImmunization = new Immunization();

        newImmunization.setDate(immunization.getDate());
        newImmunization.setImmunizationname(immunization.getImmunizationname());
        newImmunization.setClinic(immunization.getClinic());


        return immunizationRepository.save(newImmunization);
    }

    @Override
    public Immunization update(Immunization immunization, long id)
    {
        Immunization currentImmunization = immunizationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

        if (immunization.getImmunizationname() != null)
        {
            currentImmunization.setDate(immunization.getDate());
            currentImmunization.setImmunizationname(immunization.getImmunizationname());
            currentImmunization.setClinic(immunization.getClinic());
        }
        return immunizationRepository.save(currentImmunization);

    }

}