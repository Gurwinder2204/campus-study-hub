package com.campusstudyhub.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * Semester entity representing academic semesters (1-8).
 */
@Entity
@Table(name = "semesters")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Semester number must be at least 1")
    @Max(value = 8, message = "Semester number must be at most 8")
    @Column(unique = true, nullable = false)
    private Integer number;

    @Column(length = 100)
    private String name;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects = new ArrayList<>();

    // Constructors
    public Semester() {
    }

    public Semester(Integer number) {
        this.number = number;
        this.name = "Semester " + number;
    }

    public Semester(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
        subject.setSemester(this);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
        subject.setSemester(null);
    }
}
