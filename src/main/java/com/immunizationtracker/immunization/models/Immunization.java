package com.immunizationtracker.immunization.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "immunizations")
public class Immunization extends Auditable implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long immunizationid;

    private Date date;
    private String immunizationname;
    private String clinic;

    // needs foreign key to Ward
    // many to one relationship to Ward
    @ManyToOne
    @JoinColumn(name = "wardid")
    @JsonIgnoreProperties("immunizations")
    private Ward ward;

    public Immunization()
    {
    }

    public Immunization(String immunizationname, String clinic, Ward ward)
    {
        Date currDate = new Date();
        this.date = currDate;
        this.immunizationname = immunizationname;
        this.clinic = clinic;
        this.ward = ward;
    }

    public long getImmunizationid()
    {
        return immunizationid;
    }

    public void setImmunizationid(long immunizationid)
    {
        this.immunizationid = immunizationid;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getImmunizationname()
    {
        return immunizationname;
    }

    public void setImmunizationname(String immunizationname)
    {
        this.immunizationname = immunizationname;
    }

    public String getClinic()
    {
        return clinic;
    }

    public void setClinic(String clinic)
    {
        this.clinic = clinic;
    }

    public Ward getWard()
    {
        return ward;
    }
    public void setWard(Ward ward)
    {
        this.ward = ward;
    }
}
