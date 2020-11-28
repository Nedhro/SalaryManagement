package com.bs23ltd.salarymanage.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO {
    private Long id;
    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updateAt;
    private Integer activeStatus;
}
