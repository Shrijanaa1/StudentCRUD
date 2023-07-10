package com.example.StudentCRUD.Domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Course is required")
    private String course;

    @NotNull(message = "fee is required")
    @Positive(message = "Fee must be a positive value")
    private int fee;

    @NotBlank(message = "Student name is required")
    private String studentname;

    public Student() {}

    public Student(Long id, String course, int fee, String studentname) {
        this.id = id;
        this.course = course;
        this.fee = fee;
        this.studentname = studentname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

}

