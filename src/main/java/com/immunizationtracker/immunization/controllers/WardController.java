package com.immunizationtracker.immunization.controllers;

import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.models.Immunization;
import com.immunizationtracker.immunization.models.User;
import com.immunizationtracker.immunization.models.Ward;
import com.immunizationtracker.immunization.service.GuardianService;
import com.immunizationtracker.immunization.service.ImmunizationService;
import com.immunizationtracker.immunization.service.WardService;
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
@RequestMapping("/wards")
public class WardController
{
    private static final Logger logger = LoggerFactory.getLogger(GuardianController.class);


    @Autowired
    private WardService wardService;

    @Autowired
    private ImmunizationService immunizationService;

    // get all Wards
    @GetMapping(value = "/allwards", produces = {"application/json"})
    public ResponseEntity<?> listAllWards(HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        List<Ward> allWards= wardService.findAll();
        return new ResponseEntity<>(allWards, HttpStatus.OK);

    }

    // get Ward by id
    @GetMapping(value = "/ward/{wardid}", produces = {"application/json"})
    public ResponseEntity<?> getWardById(@PathVariable long wardid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        Ward w = wardService.findWardById(wardid);
        return new ResponseEntity<>(w, HttpStatus.OK);
    }

    // delete Ward by id
    @DeleteMapping("/ward/{wardid}")
    public ResponseEntity<?> deleteWardById(@PathVariable long wardid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        wardService.delete(wardid);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    // add a new ward
    @PostMapping(value = "/ward")
    public ResponseEntity<?> addNewWard(@Valid @RequestBody Ward newWard, HttpServletRequest request) throws URISyntaxException
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        newWard = wardService.save(newWard);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newWardURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{parentid}").buildAndExpand(newWard.getChildid()).toUri();
        responseHeaders.setLocation(newWardURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // update a guardian
    @PutMapping(value = "/ward/{wardid}")
    public ResponseEntity<?> updateWardById(@RequestBody Ward updateWard, @PathVariable long wardid, HttpServletRequest request)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");

        wardService.update(updateWard, wardid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // endpoint to add an immunization to the wards list
    @PutMapping(value = "/wards/{wardid}/immunization/{immunizationid}")
    public ResponseEntity<?> putGuardianToUser(HttpServletRequest request, @PathVariable long wardid, @PathVariable long immunizationid)
    {
        logger.trace(request.getRequestURI() + " accessed");
        // get immunization and ward by searching by id
        Immunization immunization = immunizationService.findImmunizationById(immunizationid);
        Ward ward = wardService.findWardById(wardid);



        wardService.putWardToImmunization(immunizationid, wardid);



        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}