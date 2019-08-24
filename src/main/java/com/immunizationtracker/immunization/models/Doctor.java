package com.immunizationtracker.immunization.models;

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
    private String name;
//    private List<Parent> hasPermissionParent = new ArrayList<>();


    public Doctor()
    {
    }

    public Doctor(String name)
    {
        this.name = name;
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
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
