package com.my.springtest.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "It shouldn't be empty.")
    @Size(min = 2, max = 15, message = "Size should be from 2 to 15")
    private String name;

    @Min(value = 0, message = "Age must be greater then 0")
    private int age;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email(message = "Incorrect format of Email")
    private String email;

    public Person() {}



    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }
}
