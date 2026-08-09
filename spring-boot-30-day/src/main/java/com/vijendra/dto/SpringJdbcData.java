package com.vijendra.dto;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class SpringJdbcData {
    public JdbcTemplate jdbcTemplate;
    public PlatformTransactionManager transactionManager;
}
