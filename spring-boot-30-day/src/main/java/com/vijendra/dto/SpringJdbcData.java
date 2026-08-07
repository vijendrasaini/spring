package com.vijendra.dto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class SpringJdbcData {
    public JdbcTemplate jdbcTemplate;
    public DataSourceTransactionManager transactionManager;
}
