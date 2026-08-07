package com.vijendra.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class SpringJDBC {
    public JdbcTemplate getJdbcTemplate() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/spring_boot_30_day");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
/*        hikariConfig.setMaximumPoolSize(1);*/
/*        hikariConfig.setConnectionTimeout(2000);*/

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return new JdbcTemplate(dataSource);
    }
}
