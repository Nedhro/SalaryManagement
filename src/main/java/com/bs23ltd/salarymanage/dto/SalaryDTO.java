package com.bs23ltd.salarymanage.dto;

import com.bs23ltd.salarymanage.enums.Grade;
import lombok.Data;

@Data
public class SalaryDTO {
    private Long id;
    private Grade grade;
    private double basic;
    private double total;
}
