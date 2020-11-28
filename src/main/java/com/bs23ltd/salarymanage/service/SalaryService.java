package com.bs23ltd.salarymanage.service;
import com.bs23ltd.salarymanage.dto.Response;
import com.bs23ltd.salarymanage.dto.SalaryDTO;
import com.bs23ltd.salarymanage.entity.Salary;
import com.bs23ltd.salarymanage.enums.Grade;

import java.util.List;

public interface SalaryService {

    public Response save(SalaryDTO salaryDTO) throws Exception;

    public Response getById(Long id);

    public Response getAll();

    public Response getByGrade(Grade grade);

    public Response deleteAll(List<Salary> salaries);
}
