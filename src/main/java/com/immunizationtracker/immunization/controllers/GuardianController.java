package com.immunizationtracker.immunization.controllers;


import com.immunizationtracker.immunization.models.ErrorDetail;
import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.models.User;
import com.immunizationtracker.immunization.models.Ward;
import com.immunizationtracker.immunization.service.DoctorService;
import com.immunizationtracker.immunization.service.GuardianService;
import com.immunizationtracker.immunization.service.UserService;
import com.immunizationtracker.immunization.service.WardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;


    @Autowired
    private WardService wardService;

    // get all Guardians
    @ApiOperation(value = "Get all guardians")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retrieved all guardians", response = void.class),
            @ApiResponse(code=404,message="Guardians not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error finding guardians", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/allguardians", produces = {"application/json"})
    public ResponseEntity<?> listAllGuardians(HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        List<Guardian> allGuardians = guardianService.findAll();
        return new ResponseEntity<>(allGuardians, HttpStatus.OK);

    }

    // get Guardian by id
    @ApiOperation(value = "Get guardian by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retrieved guardian", response = void.class),
            @ApiResponse(code=404,message="Guardian not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error finding guardian", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_GUARDIAN')")
    @GetMapping(value = "/guardian/{guardianid}", produces = {"application/json"})
    public ResponseEntity<?> getGuardianById(@PathVariable long guardianid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        Guardian g = guardianService.findGuardianById(guardianid);
        return new ResponseEntity<>(g, HttpStatus.OK);
    }

    // delete Guardian by id
    @ApiOperation(value = "Delete guardian by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retrieved guardian", response = void.class),
            @ApiResponse(code=404,message="Guardian not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error finding guardian", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_GUARDIAN')")
    @DeleteMapping("/guardian/{guardianid}")
    public ResponseEntity<?> deleteGuardianById(@PathVariable long guardianid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        guardianService.delete(guardianid);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    // add a new guardian
    @ApiOperation(value = "Onboard a new guardian")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created guardian", response = void.class),
            @ApiResponse(code = 500, message = "Error creating guardian", response = ErrorDetail.class)
    })
    @PostMapping(value = "/guardian")
    public ResponseEntity<?> addNewGuardian(@Valid @RequestBody Guardian newGuardian, HttpServletRequest request) throws URISyntaxException
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        newGuardian = guardianService.save(newGuardian);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStudentURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{guardianid}").buildAndExpand(newGuardian.getGuardianid()).toUri();
        responseHeaders.setLocation(newStudentURI);

        return new ResponseEntity<>(newGuardian, responseHeaders, HttpStatus.CREATED);
    }

    // update a guardian
    @ApiOperation(value = "Update guardian by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated guardian", response = void.class),
            @ApiResponse(code=404,message="Guardian not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error updating guardian", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_GUARDIAN')")
    @PutMapping(value = "/guardian/{guardianid}")
    public ResponseEntity<?> updateGuardianById(@RequestBody Guardian updateGuardian, @PathVariable long guardianid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        guardianService.update(updateGuardian, guardianid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // need endpoint to add a doctor to the permissions ArrayList
    @ApiOperation(value = "Add approved doctor to guardian")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Added doctor to guardian approved list", response = void.class),
            @ApiResponse(code = 500, message = "Error adding doctor to guardian approved list", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_GUARDIAN')")
    @PostMapping(value = "/guardian/{guardianid}/doctor/{doctorid}")
    public ResponseEntity<?> addApprovedDoctor(HttpServletRequest request, @PathVariable long guardianid, @PathVariable long doctorid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        // get guardian and doctor by searching by id

        doctorService.updatePermissions(doctorid, guardianid);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    // endpoint to add a guardian to the users list
    @ApiOperation(value = "Add guardian to a user profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Added guardian to user profile list", response = void.class),
            @ApiResponse(code = 500, message = "Error adding guardian to user profile list", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_GUARDIAN')")
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
    @ApiOperation(value = "Add guardian to a user profile")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Added guardian to user profile list", response = void.class),
            @ApiResponse(code = 500, message = "Error adding guardian to user profile list", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_GUARDIAN')")
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
    @ApiOperation(value = "Add a ward to a guardian")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Added ward to guardian", response = void.class),
            @ApiResponse(code = 500, message = "Error adding ward to guardian", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_GUARDIAN')")
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
