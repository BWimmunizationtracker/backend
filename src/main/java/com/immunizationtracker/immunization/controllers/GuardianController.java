package com.immunizationtracker.immunization.controllers;



import com.immunizationtracker.immunization.models.Guardian;
import com.immunizationtracker.immunization.service.GuardianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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



}
