package com.bs23ltd.salarymanage.entity;

import com.bs23ltd.salarymanage.enums.AccountType;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class BankAccount extends BaseEntity {
    private String accountName;
    private Long accountNumber;
    private AccountType accountType = AccountType.CURRENT;
    private double currentBalance;
    private String bankName;
    private String branchName;
}
