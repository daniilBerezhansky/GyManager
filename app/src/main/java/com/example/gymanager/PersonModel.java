package com.example.gymanager;

public class PersonModel {
   // private long id;
    private String firstName,secondName,email,start,end;

    public PersonModel(String firstName, String secondName, String email, String start, String end) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.start = start;
        this.end = end;
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

 /* public long getId() {
        return id;
    }*/

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
