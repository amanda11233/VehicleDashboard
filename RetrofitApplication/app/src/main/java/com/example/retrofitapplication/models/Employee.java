package com.example.retrofitapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee {

    @SerializedName("name")
    @Expose
    private String name;


    private String age;
    private String salary;
    private int id;

    public Employee(String name, String age, String salary, int id) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }
}
