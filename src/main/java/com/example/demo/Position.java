package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "position", schema = "hotel")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_ID")
    private Long roleId;

    @Column(name = "position_title", unique = true, nullable = false)
    private String title;

    @Column(name = "salary", nullable = false)
    private Double salary;

    // Getters and Setters
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
}
