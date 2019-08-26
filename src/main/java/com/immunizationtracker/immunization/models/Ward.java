package com.immunizationtracker.immunization.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wards")
public class Ward
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long wardid;

    private String firstname;
    private String lastname;


    // many to one relationship to Guardian
    // guardianid will be a foreign key

    //one to many relationship to Immunization
    // private List<Immunization> immunizations = new ArrayList<>();
    // needs foreign key to Immunization

    @OneToMany(mappedBy = "wards", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("wards")
    private List<Immunization> immunizations = new ArrayList<>();


    public Ward()
    {
    }

    public Ward(String firstname, String lastname)
    {
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public long getChildid()
    {
        return wardid;
    }

    public void setChildid(long childid)
    {
        this.wardid = childid;
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
