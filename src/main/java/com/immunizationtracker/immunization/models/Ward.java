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
    @ManyToOne
    @JoinColumn(name = "guardianid")
    @JsonIgnoreProperties("wards")
    private Guardian guardianward;


    //one to many relationship to Immunization
    // private List<Immunization> immunizations = new ArrayList<>();
    // needs foreign key to Immunization

    @OneToMany(mappedBy = "ward", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("wards")
    private List<Immunization> immunizations = new ArrayList<>();


    public Ward()
    {
    }

    public Ward(String firstname, String lastname, List<Immunization> immunizations, Guardian guardianward)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.immunizations = immunizations;
        this.guardianward = guardianward;

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

    public List<Immunization> getImmunizations()
    {
        return immunizations;
    }

    public void setImmunizations(List<Immunization> immunizations)
    {
        this.immunizations = immunizations;
    }

    public Guardian getGuardianward()
    {
        return guardianward;
    }

    public void setGuardianward(Guardian guardianward)
    {
        this.guardianward = guardianward;
    }
}





