package com.bs23ltd.salarymanage.entity;

import com.bs23ltd.salarymanage.enums.Grade;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee extends BaseEntity {
    private String employeeName;
    private Grade grade;
    private String address;
    @Column(unique = true, updatable = true)
    private Long mobile;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private BankAccount account;
}
