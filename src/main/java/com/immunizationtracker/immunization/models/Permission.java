package com.immunizationtracker.immunization.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "permissions")
public class Permission extends Auditable implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "guardianid")
    @JsonIgnoreProperties("permissions")
    private Guardian guardian;

    @Id
    @ManyToOne
    @JoinColumn(name = "doctorid")
    @JsonIgnoreProperties("permissions")
    private Doctor doctor;

    public Permission()
    {
    }

    public Permission(Guardian guardian, Doctor doctor)
    {
        this.guardian = guardian;
        this.doctor = doctor;
    }

    public Guardian getGuardian()
    {
        return guardian;
    }

    public void setGuardian(Guardian guardian)
    {
        this.guardian = guardian;
    }

    public Doctor getDoctor()
    {
        return doctor;
    }

    public void setDoctor(Doctor doctor)
    {
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Permission))
        {
            return false;
        }
        Permission permissions = (Permission) o;
        return getGuardian().equals(permissions.getGuardian()) && getDoctor().equals(permissions.getDoctor());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getGuardian(), getDoctor());
    }

}
