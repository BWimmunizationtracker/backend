package com.immunizationtracker.immunization.controllers;

import com.immunizationtracker.immunization.models.*;
import com.immunizationtracker.immunization.service.DoctorService;
import com.immunizationtracker.immunization.service.UserService;
import com.immunizationtracker.immunization.service.WardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Get all doctors")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retrieved all doctors", response = void.class),
            @ApiResponse(code=404,message="Doctors not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error finding doctors", response = ErrorDetail.class)
    })
    @GetMapping(value = "/alldoctors", produces = {"application/json"})
    public ResponseEntity<?> listAllDoctors(HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        List<Doctor> allDoctors= doctorService.findAll();
        return new ResponseEntity<>(allDoctors, HttpStatus.OK);

    }

    // get Doctor by id
    @ApiOperation(value = "Get doctor by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Doctor found", response = void.class),
            @ApiResponse(code=404,message="Doctor not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error finding doctor", response = ErrorDetail.class)
    })
    @GetMapping(value = "/doctor/{doctorid}", produces = {"application/json"})
    public ResponseEntity<?> getDoctorById(@PathVariable long doctorid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        Doctor d = doctorService.findDoctorById(doctorid);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }

    // delete Doctor by id
    @ApiOperation(value = "Delete doctor by id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Doctor deleted", response = void.class),
            @ApiResponse(code=404,message="Doctor not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error deleting doctor", response = ErrorDetail.class)
    })
    @DeleteMapping("/doctor/{doctorid}")
    public ResponseEntity<?> deleteDoctorById(@PathVariable long doctorid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        doctorService.delete(doctorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // add a new doctor
    @ApiOperation(value = "Onboard a new doctor ")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Doctor onboarded", response = void.class),
            @ApiResponse(code=404, message="Doctor not onboarded", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error onboarding doctor", response = ErrorDetail.class)
    })
    @PostMapping(value = "/doctor", consumes = {"application/json"}, produces = {"application/json"})
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
    @ApiOperation(value = "Update a new doctor by id")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Doctor updated", response = void.class),
            @ApiResponse(code=404, message="Doctor not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error updating doctor", response = ErrorDetail.class)
    })
    @PutMapping(value = "/doctor/{doctorid}", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> updateDoctorById(@RequestBody Doctor updateDoctor, @PathVariable long doctorid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        doctorService.update(updateDoctor, doctorid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // endpoint to add a doctor to the users list
    @ApiOperation(value = "Add Doctor to User profile by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Doctor added to User", response = void.class),
            @ApiResponse(code=404, message="Doctor not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error adding Doctor to User", response = ErrorDetail.class)
    })
    @PutMapping(value = "/doctor/{doctorid}/user/{userid}")
    public ResponseEntity<?> putDoctorToUser(HttpServletRequest request, @PathVariable long doctorid, @PathVariable long userid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        // get guardian and user by searching by id
        Doctor doctor = doctorService.findDoctorById(doctorid);
        User user = userService.findUserById(userid);



        doctorService.putUserToDoctor(doctorid, userid);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}