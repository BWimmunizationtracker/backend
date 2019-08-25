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
    private long parentid;

    private String firstname;
    private String lastname;

    // many:many Guardian:Doctor
    @OneToMany(mappedBy = "guardians", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("guardians")
    private List<Permission> permissions = new ArrayList<>();

//    private List<Child> children = new ArrayList<>();

    // create base constructor

    public Guardian()
    {
    }

    // create constructor

    public Guardian(String firstname, String lastname, List<Permission> permissions)
    {
        this.firstname = firstname;
        this.lastname = lastname;

        for (Permission p : permissions)
        {
            p.setGuardian(this);
        }

        this.permissions = permissions;
    }


    // getter and setters

    public long getParentid()
    {
        return parentid;
    }

    public void setParentid(long parentid)
    {
        this.parentid = parentid;
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
}
