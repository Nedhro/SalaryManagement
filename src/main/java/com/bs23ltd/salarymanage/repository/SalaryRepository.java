package com.bs23ltd.salarymanage.repository;

import com.bs23ltd.salarymanage.entity.Salary;
import com.bs23ltd.salarymanage.enums.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
    @Query("from Salary where grade=:grade")
    Optional<Salary> findByGrade(Grade grade);
}
