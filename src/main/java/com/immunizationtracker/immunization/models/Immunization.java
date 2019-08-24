package com.immunizationtracker.immunization.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "immunizations")
public class Immunization extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long immunizationid;

    private Date date;
    private String immunizationname;
    private String clinic;

    // needs foreign key to Ward

    public Immunization()
    {
    }

    public Immunization(String immunizationname, String clinic)
    {
        Date currDate = new Date();
        this.date = currDate;
        this.immunizationname = immunizationname;
        this.clinic = clinic;
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
}
