package com.immunizationtracker.immunization.controllers;

import com.immunizationtracker.immunization.models.Doctor;
import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.models.User;
import com.immunizationtracker.immunization.models.Ward;
import com.immunizationtracker.immunization.service.DoctorService;
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

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController
{
    private static final Logger logger = LoggerFactory.getLogger(GuardianController.class);


    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    // get all Doctors
    @GetMapping(value = "/alldoctors", produces = {"application/json"})
    public ResponseEntity<?> listAllDoctors(HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        List<Doctor> allDoctors= doctorService.findAll();
        return new ResponseEntity<>(allDoctors, HttpStatus.OK);

    }

    // get Doctor by id
    @GetMapping(value = "/doctor/{doctorid}", produces = {"application/json"})
    public ResponseEntity<?> getDoctorById(@PathVariable long doctorid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        Doctor d = doctorService.findDoctorById(doctorid);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    // delete Doctor by id
    @DeleteMapping("/doctor/{doctorid}")
    public ResponseEntity<?> deleteDoctorById(@PathVariable long doctorid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        doctorService.delete(doctorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // add a new doctor
    @PostMapping(value = "/doctor")
    public ResponseEntity<?> addNewDoctor(@Valid @RequestBody Doctor newDoctor, HttpServletRequest request) throws URISyntaxException
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        newDoctor = doctorService.save(newDoctor);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newDoctorURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{doctorid}").buildAndExpand(newDoctor.getDoctorid()).toUri();
        responseHeaders.setLocation(newDoctorURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // update a doctor
    @PutMapping(value = "/doctor/{doctorid}")
    public ResponseEntity<?> updateDoctorById(@RequestBody Doctor updateDoctor, @PathVariable long doctorid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        doctorService.update(updateDoctor, doctorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // endpoint to add a doctor to the users list
    @PutMapping(value = "/doctor/{doctorid}/user/{userid}")
    public ResponseEntity<?> putGuardianToUser(HttpServletRequest request, @PathVariable long doctorid, @PathVariable long userid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        // get guardian and user by searching by id
        Doctor doctor = doctorService.findDoctorById(doctorid);
        User user = userService.findUserById(userid);



        doctorService.putUserToDoctor(doctorid, userid);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}