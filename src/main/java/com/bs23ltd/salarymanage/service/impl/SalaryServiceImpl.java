package com.bs23ltd.salarymanage.service.impl;

import com.bs23ltd.salarymanage.dto.Response;
import com.bs23ltd.salarymanage.dto.SalaryDTO;
import com.bs23ltd.salarymanage.entity.Salary;
import com.bs23ltd.salarymanage.enums.Grade;
import com.bs23ltd.salarymanage.repository.SalaryRepository;
import com.bs23ltd.salarymanage.service.SalaryService;
import com.bs23ltd.salarymanage.util.ResponseBuilder;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;
    private final ModelMapper modelMapper;
    private final String root = "Salary";
    private List<Salary> salaries;

    public SalaryServiceImpl(SalaryRepository salaryRepository, ModelMapper modelMapper) {
        this.salaryRepository = salaryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response save(SalaryDTO salaryDTO) throws Exception {
        Salary salary = null;
        if (salaryDTO.getId() != null) {
            salary = salaryRepository.findById(salaryDTO.getId()).orElseThrow(() -> new NotFoundException("Salary not found"));
        } else {
            salary = new Salary();
        }
        salary.setGrade(salaryDTO.getGrade());
        salary.setBasic(salaryDTO.getBasic());
        double basic = salaryDTO.getBasic();
        salary.setTotal(this.calculateTotal(basic));
        salary = salaryRepository.save(salary);
        this.updateOtherGrade(salary);
        if (salary != null) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " has been Created :: ", salary);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public Response getById(Long id) {
        Optional<Salary> salary = salaryRepository.findById(id);
        if (salary != null) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", salary);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
    }

    @Override
    public Response getAll() {
        List<Salary> salaryList = salaryRepository.findAll();
        if (salaryList.isEmpty()) {
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", salaryList);
    }

    @Override
    public Response getByGrade(Grade grade) {
        Optional<Salary> salary = salaryRepository.findByGrade(grade);
        if (salary != null) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", salary);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
    }

    @Override
    public Response deleteAll(List<Salary> salaries) {
        salaryRepository.deleteAll(salaries);
        return ResponseBuilder.getSuccessResponse(HttpStatus.NO_CONTENT, root + " deleted Successfully", salaries);
    }

    @Transactional
    public void updateOtherGrade(Salary salary) throws Exception {
        System.out.println(salary.getBasic());
        double basic6 = salary.getBasic();
        double basic5 = basic6 + 5000;
        double total5 = this.calculateTotal(basic5);
        double basic4 = basic5 + 5000;
        double total4 = this.calculateTotal(basic4);
        double basic3 = basic4 + 5000;
        double total3 = this.calculateTotal(basic3);
        double basic2 = basic3 + 5000;
        double total2 = this.calculateTotal(basic2);
        double basic1 = basic2 + 5000;
        double total1 = this.calculateTotal(basic1);

        Salary salary5 = new Salary(Grade.FIVE, basic5, total5);
        Salary salary4 = new Salary(Grade.FOUR, basic4, total4);
        Salary salary3 = new Salary(Grade.THREE, basic3, total3);
        Salary salary2 = new Salary(Grade.TWO, basic2, total2);
        Salary salary1 = new Salary(Grade.ONE, basic1, total1);
        List<Salary> stringList = new ArrayList<>();
        stringList.add(salary5);
        stringList.add(salary4);
        stringList.add(salary3);
        stringList.add(salary2);
        stringList.add(salary1);
        salaryRepository.saveAll(stringList);
        System.out.println(salary1 + "," + salary2 + "," + salary3 + "," + salary4 + "," + salary5);
    }

    @Transactional
    public double calculateTotal(double basic) {
        double homeRent = basic * 20 / 100;
        double medicalAllownce = basic * 15 / 100;
        double total = basic + homeRent + medicalAllownce;
        return total;
    }
}
