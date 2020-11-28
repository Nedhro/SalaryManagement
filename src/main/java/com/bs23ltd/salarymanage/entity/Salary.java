package com.bs23ltd.salarymanage.entity;

import com.bs23ltd.salarymanage.enums.Grade;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, updatable = true)
    private Grade grade = Grade.SIX;
    private double basic;
    private double total;

    public Salary(Grade grade, double basic, double total) {
        this.grade = grade;
        this.basic = basic;
        this.total = total;
    }

    public Salary() {
    }
}
