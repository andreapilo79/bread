package com.mycompany.bread.domain.customer;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstname;

    private String lastname;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar dateOfBirth;

    @Override
    public String toString()
    {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("firstname", firstname)
            .append("lastname", lastname)
            .append("dateOfBirth", dateOfBirth)
            .toString();
    }

    public Customer()
    {
    }

    public Customer(String firstname, String lastname, GregorianCalendar dateOfBirth)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public GregorianCalendar getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(GregorianCalendar dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }
}
