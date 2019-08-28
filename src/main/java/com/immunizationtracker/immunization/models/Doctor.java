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

    // many to one relationship to User
    // userid will be a foreign key
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties("userDoctors")
    private User user;


    public Doctor()
    {
    }



    public Doctor(String name)
    {
        this.doctorname = name;
    }

    public Doctor(String name, User user)
    {
        this.doctorname = name;
        this.user = user;
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

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
