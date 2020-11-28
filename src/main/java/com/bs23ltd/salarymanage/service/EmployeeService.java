package com.bs23ltd.salarymanage.service;

import com.bs23ltd.salarymanage.dto.BankAccountDTO;
import com.bs23ltd.salarymanage.dto.EmployeeDTO;
import com.bs23ltd.salarymanage.dto.Response;

public interface EmployeeService {

    public Response save(EmployeeDTO employeeDTO) throws Exception;

    public Response getById(Long id);

    public Response getAll();

    Response updateBalance(Long accountId, BankAccountDTO bankAccountDTO);

    Response disburseSalary(Long accountId, BankAccountDTO bankAccountDTO);
}
