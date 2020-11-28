package com.bs23ltd.salarymanage.dto;

import com.bs23ltd.salarymanage.enums.AccountType;
import lombok.Data;

@Data
public class BankAccountDTO extends BaseDTO {
    private String accountName;
    private Long accountNumber;
    private AccountType accountType;
    private double currentBalance;
    private String bankName;
    private String branchName;
}
