package com.immunizationtracker.immunization.controllers;

import com.immunizationtracker.immunization.models.ErrorDetail;
import com.immunizationtracker.immunization.models.Immunization;
import com.immunizationtracker.immunization.service.ImmunizationService;
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
    @ApiOperation(value = "Get all immunizations")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retrieved all immunizations", response = void.class),
            @ApiResponse(code=404,message="immunizations not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error finding immunizations", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    @GetMapping(value = "/allimmunizations", produces = {"application/json"})
    public ResponseEntity<?> listAllImmunization(HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        List<Immunization> allImmunizations = immunizationService.findAll();
        return new ResponseEntity<>(allImmunizations, HttpStatus.OK);

    }

    // get Immunization by id
    @ApiOperation(value = "Get immunization by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Retrieved immunization", response = void.class),
            @ApiResponse(code=404,message="immunization not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error finding immunization", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/immunization/{immunizationid}", produces = {"application/json"})
    public ResponseEntity<?> getImmunizationById(@PathVariable long immunizationid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        Immunization i = immunizationService.findImmunizationById(immunizationid);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    // delete Immunization by id
    @ApiOperation(value = "Delete immunization by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Deleted immunization", response = void.class),
            @ApiResponse(code=404,message="Immunization not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error finding immunization", response = ErrorDetail.class)
    })
//    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    @DeleteMapping("/immunization/{immunizationid}")
    public ResponseEntity<?> deleteImmunizationById(@PathVariable long immunizationid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        immunizationService.delete(immunizationid);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    // add a new immunization
    @ApiOperation(value = "Create a new immunization")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created immunization", response = void.class),
            @ApiResponse(code = 500, message = "Error creating immunization", response = ErrorDetail.class)
    })
    //    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
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
    @ApiOperation(value = "Update an immunization by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Updated immunization", response = void.class),
            @ApiResponse(code=404,message="Immunization not found", response = ErrorDetail.class),
            @ApiResponse(code = 500, message = "Error updating immunization", response = ErrorDetail.class)
    })
    @PutMapping(value = "/immunization/{immunizationid}")
    public ResponseEntity<?> updateImmunizationById(@RequestBody Immunization updateImmunization, @PathVariable long immunizationid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        immunizationService.update(updateImmunization, immunizationid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
