package com.immunizationtracker.immunization.controllers;



import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.service.GuardianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/guardians")
public class GuardianController
{
    private static final Logger logger = LoggerFactory.getLogger(GuardianController.class);


    @Autowired
    private GuardianService guardianService;

    // get all Guardians
    @GetMapping(value = "/allguardians", produces = {"application/json"})
    public ResponseEntity<?> listAllGuardians(HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        List<Guardian> allGuardians = guardianService.findAll();
        return new ResponseEntity<>(allGuardians, HttpStatus.OK);

    }

    // get Guardian by id
    @GetMapping(value = "/guardian/{guardianid}", produces = {"application/json"})
    public ResponseEntity<?> getGuardianById(@PathVariable long guardianid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        Guardian g = guardianService.findGuardianById(guardianid);
        return new ResponseEntity<>(g, HttpStatus.OK);
    }

    // delete Guardian by id
    @DeleteMapping("/guardian/{guardianid}")
    public ResponseEntity<?> deleteGuardianById(@PathVariable long guardianid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        guardianService.delete(guardianid);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    // add a new guardian
    @PostMapping(value = "/guardian")
    public ResponseEntity<?> addNewGuardian(@Valid @RequestBody Guardian newGuardian, HttpServletRequest request) throws URISyntaxException
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        newGuardian = guardianService.save(newGuardian);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{parentid}").buildAndExpand(newGuardian.getParentid()).toUri();
        responseHeaders.setLocation(newStudentURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // update a guardian
    @PutMapping(value = "/guardian/{guardianid}")
    public ResponseEntity<?> updateGuardianById(@RequestBody Guardian updateGuardian, @PathVariable long guardianid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        guardianService.update(updateGuardian, guardianid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
