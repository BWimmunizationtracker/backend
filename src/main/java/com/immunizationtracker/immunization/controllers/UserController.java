package com.immunizationtracker.immunization.controllers;

import com.immunizationtracker.immunization.models.ErrorDetail;
import com.immunizationtracker.immunization.models.User;
import com.immunizationtracker.immunization.service.UserService;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    private UserService userService;

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users",
            produces = {"application/json"})
    public ResponseEntity<?> listAllUsers(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieves the current logged in user",
            response = User.class)
    @ApiResponses({@ApiResponse(code = 200,
            message = "User found",
            response = User.class), @ApiResponse(code = 404,
            message = "User not found",
            response = ErrorDetail.class)})
//    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/user",
            produces = {"application/json"})
    public ResponseEntity<?> retrieveCurrentUser(HttpServletRequest request, Authentication authentication)
    {
        logger.info(request.getMethod() + " " + request.getRequestURI() + " accessed");
        User currentUser = userService.findUserByUsername(authentication.getName());
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }


//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/{userid}",
            produces = {"application/json"})
    public ResponseEntity<?> getUser(HttpServletRequest request,
                                     @PathVariable
                                             Long userid)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findUserById(userid);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/getusername",
            produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(HttpServletRequest request, Authentication authentication)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }



    @PostMapping(value = "/user",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping(value = "/userdoctor",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewUserDoctor(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {
        newuser = userService.saveDoctor(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(newuser, responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping(value = "/userguardian",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addNewUserGuardian(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {
        newuser = userService.saveGuardian(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(newuser, responseHeaders, HttpStatus.CREATED);
    }


//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "/user/{userid}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                User updateUser,
                                        @PathVariable
                                                long userid)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.update(updateUser, userid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request,
                                            @PathVariable
                                                    long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}