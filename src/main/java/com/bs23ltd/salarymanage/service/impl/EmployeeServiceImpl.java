package com.bs23ltd.salarymanage.service.impl;

import com.bs23ltd.salarymanage.dto.BankAccountDTO;
import com.bs23ltd.salarymanage.dto.EmployeeDTO;
import com.bs23ltd.salarymanage.dto.Response;
import com.bs23ltd.salarymanage.entity.BankAccount;
import com.bs23ltd.salarymanage.entity.Employee;
import com.bs23ltd.salarymanage.enums.ActiveStatus;
import com.bs23ltd.salarymanage.repository.BankAccountRepository;
import com.bs23ltd.salarymanage.repository.EmployeeRepository;
import com.bs23ltd.salarymanage.service.EmployeeService;
import com.bs23ltd.salarymanage.util.ResponseBuilder;
import javassist.NotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final String root = "Employee";

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response save(EmployeeDTO employeeDTO) throws Exception {
        Employee employee = null;
        if (employeeDTO.getId() != null) {
            employee = employeeRepository.findById(employeeDTO.getId()).orElseThrow(() -> new NotFoundException("Employee not found"));
        } else {
            employee = new Employee();
        }
        employee = modelMapper.map(employeeDTO, Employee.class);
        employee = employeeRepository.save(employee);
        if (employee != null) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " has been Created :: ", employee);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public Response getById(Long id) {
        Employee employee = employeeRepository.getByIdAndActiveStatusTrue(id, ActiveStatus.ACTIVE.getValue());
        if (employee != null) {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieved Successfully", employeeDTO);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
    }

    @Override
    public Response getAll() {
        List<Employee> employeeList = employeeRepository.list(ActiveStatus.ACTIVE.getValue());
        List<EmployeeDTO> employeeDTOList = this.getEmployees(employeeList);
        if (employeeDTOList.isEmpty() || employeeDTOList == null) {
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "There is No Employee");
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + "Data Retrieve Successfully", employeeDTOList);
    }

    @Override
    public Response updateBalance(Long accountId, BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = this.getByAccountId(accountId);
        if (bankAccount != null) {
            bankAccount.setId(accountId);
            bankAccount.setAccountName(bankAccount.getAccountName());
            bankAccount.setAccountNumber(bankAccount.getAccountNumber());
            bankAccount.setAccountType(bankAccount.getAccountType());
            double previousBalance = bankAccount.getCurrentBalance();
            double newBalance = bankAccountDTO.getCurrentBalance();
            double balance = previousBalance + newBalance;
            bankAccount.setCurrentBalance(balance);
            bankAccount.setBankName(bankAccount.getBankName());
            bankAccount.setBranchName(bankAccount.getBranchName());
            bankAccount = bankAccountRepository.save(bankAccount);
            if (bankAccount != null) {
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Bank Account updated Successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
    }

    @Override
    public Response disburseSalary(Long accountId, BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = this.getByAccountId(accountId);
        if (bankAccount != null && accountId == 1) {
            bankAccount.setId(accountId);
            bankAccount.setAccountName(bankAccount.getAccountName());
            bankAccount.setAccountNumber(bankAccount.getAccountNumber());
            bankAccount.setAccountType(bankAccount.getAccountType());
            double previousBalance = bankAccount.getCurrentBalance();
            double newBalance = bankAccountDTO.getCurrentBalance();
            if (previousBalance < newBalance) {
                return ResponseBuilder.getFailureResponse(HttpStatus.EXPECTATION_FAILED, "Don't Have enough balance, Add Money Please");
            }
            double balance = previousBalance - newBalance;
            bankAccount.setCurrentBalance(balance);
            bankAccount.setBankName(bankAccount.getBankName());
            bankAccount.setBranchName(bankAccount.getBranchName());
            bankAccount = bankAccountRepository.save(bankAccount);
            if (bankAccount != null) {
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Salary Disbursed Successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }else{
            this.updateBalance(accountId,bankAccountDTO);
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
    }

    public BankAccount getByAccountId(Long accountId) {
        return employeeRepository.getByAccount_Id(accountId);
    }

    private List<EmployeeDTO> getEmployees(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employees.forEach(employee -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
            employeeDTOList.add(employeeDTO);
        });
        return employeeDTOList;
    }
}
