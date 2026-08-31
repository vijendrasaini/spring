package com.vijendra.dto;

import java.math.BigDecimal;

/**
 * Interface projection for native SQL — column aliases in query must match getter names.
 * Example: {@code d.name AS deptName} → {@code getDeptName()}.
 */
public interface EmployeeDeptSummary {
    Integer getId();

    String getName();

    BigDecimal getSalary();

    String getDeptName();
}
