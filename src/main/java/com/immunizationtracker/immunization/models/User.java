package com.immunizationtracker.immunization.models;

// User is considered the parent entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false,
            unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    //one to many relationship to UserRoles

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userRoles = new ArrayList<>();

    // one to many relationship to Guardian

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Guardian> userGuardians = new ArrayList<>();

    // one to many relationship to Doctor

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Doctor> userDoctors = new ArrayList<>();

    public User()
    {
    }

    public User(String username, String password, List<UserRoles> userRoles)
    {
        setUsername(username);
        setPassword(password);

        for (UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userRoles = userRoles;
    }

    public User(String username, String password, List<UserRoles> userRoles, List<Guardian> userGuardians)
    {
        setUsername(username);
        setPassword(password);

        for (UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userRoles = userRoles;
        this.userGuardians = userGuardians;
    }

    public User(String username, String password, List<UserRoles> userRoles, List<Guardian> userGuardians, List<Doctor> userDoctors)
    {
        setUsername(username);
        setPassword(password);

        for (UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userRoles = userRoles;
        this.userGuardians = userGuardians;
        this.userDoctors = userDoctors;
    }

//    public User(String username, String password)
//    {
//        setUsername(username);
//        setPassword(password);
//        ArrayList<UserRoles> newRoles = new ArrayList<>();
//        newRoles.add(new UserRoles(newUser, roleService.findByName("doctor")));
//        newUser.setUserRoles(newRoles);
//        this.userRoles = new Use
//
//    }


    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public List<UserRoles> getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles)
    {
        this.userRoles = userRoles;
    }

    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userRoles)
        {
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }

    public List<Guardian> getUserGuardians()
    {
        return userGuardians;
    }

    public void setUserGuardians(List<Guardian> userGuardians)
    {
        this.userGuardians = userGuardians;
    }

    public List<Doctor> getUserDoctors()
    {
        return userDoctors;
    }

    public void setUserDoctors(List<Doctor> userDoctors)
    {
        this.userDoctors = userDoctors;
    }
}