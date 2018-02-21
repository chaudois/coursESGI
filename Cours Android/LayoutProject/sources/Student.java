package com.damie.layoutproject;

/**
 * Created by damie on 05/10/2017.
 */

public class Student {
    String Name;
    String firstName;

    public Student(String name, String firstName) {
        Name = name;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
