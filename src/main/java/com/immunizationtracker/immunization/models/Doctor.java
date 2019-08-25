package com.immunizationtracker.immunization.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Parent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long doctorid;

    private String doctorname;

    // many:many Doctor:Guardian
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("doctors")
    private List<Permission> permissions = new ArrayList<>();


    public Doctor()
    {
    }



    public Doctor(String name)
    {
        this.doctorname = name;
    }

    public long getDoctorid()
    {
        return doctorid;
    }

    public void setDoctorid(long doctorid)
    {
        this.doctorid = doctorid;
    }

    public String getName()
    {
        return doctorname;
    }

    public void setName(String name)
    {
        this.doctorname = name;
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
