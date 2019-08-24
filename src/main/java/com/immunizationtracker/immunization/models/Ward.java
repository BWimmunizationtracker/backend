package com.immunizationtracker.immunization.models;

import javax.persistence.*;

@Entity
@Table(name = "wards")
public class Ward
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long childid;

    private String firstname;
    private String lastname;

    // parentid will be a foreign key
    // private List<Immunization> immunizations = new ArrayList<>();


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
        return childid;
    }

    public void setChildid(long childid)
    {
        this.childid = childid;
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
