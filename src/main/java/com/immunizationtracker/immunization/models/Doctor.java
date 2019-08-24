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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "permissionGrantedBy", joinColumns = {@JoinColumn(name = "doctorid")}, inverseJoinColumns = {@JoinColumn(name = "parentid")})
    @JsonIgnoreProperties("doctors")
    private List<Guardian> hasPermissionGuardian = new ArrayList<>();


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


    public List<Guardian> getHasPermissionGuardian()
    {
        return hasPermissionGuardian;
    }

    public void setHasPermissionGuardian(List<Guardian> hasPermissionGuardian)
    {
        this.hasPermissionGuardian = hasPermissionGuardian;
    }
}
