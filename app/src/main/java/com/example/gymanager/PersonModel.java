package com.example.gymanager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
//@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonModel {


    private Long id;
    private String firstName,secondName,email;
    private Date dateStart,dateEnd;

    public PersonModel(String firstName, String secondName, String email, Date dateStart, Date dateEnd) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;

    }
    public PersonModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
