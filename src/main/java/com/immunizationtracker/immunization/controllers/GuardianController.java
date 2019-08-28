package com.immunizationtracker.immunization.controllers;



import com.immunizationtracker.immunization.models.*;
import com.immunizationtracker.immunization.service.DoctorService;
import com.immunizationtracker.immunization.service.GuardianService;
import com.immunizationtracker.immunization.service.UserService;
import com.immunizationtracker.immunization.service.WardService;
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
import java.awt.print.Book;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/guardians")
public class GuardianController
{
    private static final Logger logger = LoggerFactory.getLogger(GuardianController.class);


    @Autowired
    private GuardianService guardianService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;


    @Autowired
    private WardService wardService;

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

//    // add a new guardian
//    @PostMapping(value = "/guardian")
//    public ResponseEntity<?> addNewGuardian(@Valid @RequestBody Guardian newGuardian, HttpServletRequest request) throws URISyntaxException
//    {
//        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");
//
//        newGuardian = guardianService.save(newGuardian);
//
//        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{guardianid}").buildAndExpand(newGuardian.getGuardianid()).toUri();
//        responseHeaders.setLocation(newStudentURI);
//
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
//    }

    // add a new guardian
    @PostMapping(value = "/guardian")
    public ResponseEntity<?> addNewGuardian(@Valid @RequestBody Guardian newGuardian, HttpServletRequest request) throws URISyntaxException
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        newGuardian = guardianService.save(newGuardian);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{guardianid}").buildAndExpand(newGuardian.getGuardianid()).toUri();
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

    // need endpoint to add a doctor to the permissions ArrayList
    @PostMapping(value = "/guardian/{guardianid}/doctor/{doctorid}")
    public ResponseEntity<?> addApprovedDoctor(HttpServletRequest request, @PathVariable long guardianid, @PathVariable long doctorid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        // get guardian and doctor by searching by id

        doctorService.updatePermissions(doctorid, guardianid);



        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    // endpoint to add a guardian to the users list
    @PostMapping(value = "/guardian/{guardianid}/user/{userid}")
    public ResponseEntity<?> addGuardianToUser(HttpServletRequest request, @PathVariable long guardianid, @PathVariable long userid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        // get guardian and user by searching by id
        Guardian guardian = guardianService.findGuardianById(guardianid);
        User user = userService.findUserById(userid);



        guardianService.putUserToGuardian(guardianid, userid);



        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    // endpoint to add a guardian to the users list
    @PutMapping(value = "/guardian/{guardianid}/user/{userid}")
    public ResponseEntity<?> putGuardianToUser(HttpServletRequest request, @PathVariable long guardianid, @PathVariable long userid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        // get guardian and user by searching by id
        Guardian guardian = guardianService.findGuardianById(guardianid);
        User user = userService.findUserById(userid);



        guardianService.putUserToGuardian(guardianid, userid);



        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    // endpoint to add a ward to the guardians list
    @PutMapping(value = "/guardian/{guardianid}/ward/{wardid}")
    public ResponseEntity<?> putGuardianToWard(HttpServletRequest request, @PathVariable long wardid, @PathVariable long guardianid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        // get ward and guardian by searching by id
        Ward ward = wardService.findWardById(wardid);
        Guardian guardian = guardianService.findGuardianById(guardianid);



        wardService.putGuardianToWard(wardid, guardianid);



        return new ResponseEntity<>(HttpStatus.CREATED);

    }





}
