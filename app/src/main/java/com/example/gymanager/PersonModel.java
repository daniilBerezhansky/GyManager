package com.example.gymanager;

public class PersonModel {
    private long id;
    private String firstName,secondName,email;

    public PersonModel(String firstName, String secondName, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }
    public PersonModel(){

    }
    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }


}
