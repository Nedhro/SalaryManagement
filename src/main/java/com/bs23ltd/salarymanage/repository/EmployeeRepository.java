package com.bs23ltd.salarymanage.repository;

import com.bs23ltd.salarymanage.entity.BankAccount;
import com.bs23ltd.salarymanage.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("from Employee where activeStatus = :activeStatus")
    List<Employee> list(@Param("activeStatus") Integer activeStatus);

    @Query("from Employee where id = :id and activeStatus = :activeStatus")
    Employee getByIdAndActiveStatusTrue(@Param("id") Long id, @Param("activeStatus") Integer activeStatus);

    @Query("from BankAccount where id=:accountId")
    BankAccount getByAccount_Id(Long accountId);
}
