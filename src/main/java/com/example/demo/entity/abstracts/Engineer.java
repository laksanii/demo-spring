package com.example.demo.entity.abstracts;

public class Engineer extends Employee {
    private int salary;
    private String grade;

    void setSalary(int salary) {
        this.salary = salary;
    }

    int getSalary() {
        return salary;
    }

    void setGrade(String grade) {
        this.grade = grade;
    }

    String getGrade() {
        return grade;
    }


    void label(){
        System.out.println("Engineer's data:");
    }
}
