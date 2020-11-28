package com.bs23ltd.salarymanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/employeeInfo")
    public String employeeList() {
        return "views/employees";
    }

    @RequestMapping("/employee/add")
    public String newEmployee() {
        return "views/newEmployee";
    }

    @RequestMapping("/companyInfo")
    public String companyInfo() {
        return "views/company";
    }

    @RequestMapping("/addBasic")
    public String addBasic() {
        return "views/salary";
    }

    @RequestMapping("/disbursement")
    public String salaryDisbursement() {
        return "views/disbursement";
    }
}
