package com.immunizationtracker.immunization.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guardians")
public class Guardian extends Auditable
{
    // fields - state - values
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long guardianid;

    private String firstname;
    private String lastname;

    // many:many Guardian:Doctor split up into permissions table
    @OneToMany(mappedBy = "guardian", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("guardian")
    private List<Permission> permissions = new ArrayList<>();

//    Guardian will have a one to many relationship to Ward
    @OneToMany(mappedBy = "guardianward", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("guardianward")
    private List<Ward> guardianwards = new ArrayList<>();

    // many to one relationship to User
    // userid will be a foreign key
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userGuardians")
    private User user;

//    private List<Child> children = new ArrayList<>();

    // create base constructor

    public Guardian()
    {
    }

    // create constructor

    public Guardian(String firstname, String lastname, List<Permission> permissions, List<Ward> wards)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.guardianwards = wards;

        for (Permission p : permissions)
        {
            p.setGuardian(this);
        }

        this.permissions = permissions;
    }

    public Guardian(String firstname, String lastname, List<Permission> permissions, List<Ward> wards, User user)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.guardianwards = wards;

        for (Permission p : permissions)
        {
            p.setGuardian(this);
        }

        this.permissions = permissions;
        this.user = user;
    }


    // getter and setters

    public long getGuardianid()
    {
        return guardianid;
    }

    public void setGuardianid(long guardianid)
    {
        this.guardianid = guardianid;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public List<Permission> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions)
    {
        this.permissions = permissions;
    }

    public List<Ward> getWards()
    {
        return guardianwards;
    }

    public void setWards(List<Ward> wards)
    {
        this.guardianwards = wards;
    }


    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
