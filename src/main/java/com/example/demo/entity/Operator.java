package com.example.demo.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "operators")
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String department;

    @ManyToMany(mappedBy = "operators")
    private Set<ApplicationRequest> requests = new HashSet<>();

    public Operator() {
    }

    public Operator(Long id, String name, String surname, String department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<ApplicationRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<ApplicationRequest> requests) {
        this.requests = requests;
    }
}