package com.bs23ltd.salarymanage.controller;

import com.bs23ltd.salarymanage.annotation.ApiController;
import com.bs23ltd.salarymanage.dto.Response;
import com.bs23ltd.salarymanage.dto.SalaryDTO;
import com.bs23ltd.salarymanage.entity.Salary;
import com.bs23ltd.salarymanage.enums.Grade;
import com.bs23ltd.salarymanage.service.SalaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiController
@RequestMapping("/salary")
public class SalaryController {
    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PostMapping
    public Response save(@RequestBody SalaryDTO salaryDTO) throws Exception {
        return salaryService.save(salaryDTO);
    }

    @GetMapping
    public Response getAll() {
        return salaryService.getAll();
    }

    @GetMapping("/{salaryId}")
    public Response getById(@PathVariable("salaryId") Long salaryId) {
        return salaryService.getById(salaryId);
    }

    @GetMapping("/grade/{grade}")
    public Response getByGrade(@PathVariable("grade") Grade grade) {
        return salaryService.getByGrade(grade);
    }

    @RequestMapping(value = "/deleteall",method = RequestMethod.DELETE)
    public Response deleteAll(@RequestBody List<Salary> salaries){
        return salaryService.deleteAll(salaries);
    }
}
