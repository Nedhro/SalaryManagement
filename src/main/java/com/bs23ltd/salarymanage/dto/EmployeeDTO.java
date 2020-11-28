package com.bs23ltd.salarymanage.dto;

import com.bs23ltd.salarymanage.entity.BankAccount;
import com.bs23ltd.salarymanage.enums.Grade;
import lombok.Data;

@Data
public class EmployeeDTO extends BaseDTO {
    private String employeeName;
    private Grade grade;
    private String address;
    private Long mobile;
    private BankAccount account;
}
