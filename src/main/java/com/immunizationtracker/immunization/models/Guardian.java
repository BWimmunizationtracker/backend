package com.immunizationtracker.immunization.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

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
//    private List<Doctor> gavePermissionDoctor = new ArrayList<>();
//    private List<Child> children = new ArrayList<>();

    // create base constructor

    public Guardian()
    {
    }

    // create constructor


    public Guardian(String firstname, String lastname)
    {
        this.firstname = firstname;
        this.lastname = lastname;
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
}
