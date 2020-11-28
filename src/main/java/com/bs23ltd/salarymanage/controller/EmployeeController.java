package com.bs23ltd.salarymanage.controller;

import com.bs23ltd.salarymanage.annotation.ApiController;
import com.bs23ltd.salarymanage.dto.BankAccountDTO;
import com.bs23ltd.salarymanage.dto.EmployeeDTO;
import com.bs23ltd.salarymanage.dto.Response;
import com.bs23ltd.salarymanage.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@ApiController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Response save(@RequestBody EmployeeDTO employeeDTO) throws Exception {
        return employeeService.save(employeeDTO);
    }

    @GetMapping
    public Response getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{employeeId}")
    public Response getById(@PathVariable("employeeId") Long employeeId) {
        return employeeService.getById(employeeId);
    }

    @PutMapping("/account/{accountId}")
    public Response updateBalance(@RequestBody BankAccountDTO bankAccountDTO, @PathVariable("accountId") Long accountId) {
        return employeeService.updateBalance(accountId, bankAccountDTO);
    }

    @PutMapping("/disburse/{accountId}")
    public Response disburseSalary(@RequestBody BankAccountDTO bankAccountDTO, @PathVariable("accountId") Long accountId) {
        return employeeService.disburseSalary(accountId, bankAccountDTO);
    }


}
