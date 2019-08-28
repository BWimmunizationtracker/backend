package com.immunizationtracker.immunization.controllers;

import com.immunizationtracker.immunization.models.Immunization;
import com.immunizationtracker.immunization.service.ImmunizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/immunizations")
public class ImmunizationController
{
    private static final Logger logger = LoggerFactory.getLogger(GuardianController.class);


    @Autowired
    private ImmunizationService immunizationService;

    // get all Immunizations
    @GetMapping(value = "/allimmunizations", produces = {"application/json"})
    public ResponseEntity<?> listAllImmunization(HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        List<Immunization> allImmunizations = immunizationService.findAll();
        return new ResponseEntity<>(allImmunizations, HttpStatus.OK);

    }

    // get Immunization by id
    @GetMapping(value = "/immunization/{immunizationid}", produces = {"application/json"})
    public ResponseEntity<?> getImmunizationById(@PathVariable long immunizationid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        Immunization i = immunizationService.findImmunizationById(immunizationid);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    // delete Immunization by id
    @DeleteMapping("/immunization/{immunizationid}")
    public ResponseEntity<?> deleteImmunizationById(@PathVariable long immunizationid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        immunizationService.delete(immunizationid);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    // add a new immunization
    @PostMapping(value = "/immunization")
    public ResponseEntity<?> addNewImmunization(@Valid @RequestBody Immunization newImmunization, HttpServletRequest request) throws URISyntaxException
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        newImmunization = immunizationService.save(newImmunization);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newImmunizationURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{parentid}").buildAndExpand(newImmunization.getImmunizationid()).toUri();
        responseHeaders.setLocation(newImmunizationURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // update an immunization
    @PutMapping(value = "/immunization/{immunizationid}")
    public ResponseEntity<?> updateImmunizationById(@RequestBody Immunization updateImmunization, @PathVariable long immunizationid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        immunizationService.update(updateImmunization, immunizationid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
