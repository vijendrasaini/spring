package com.vijendra.day2;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HikariCPDemo {
    static void main(String[] args) throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/spring_boot_30_day");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setMaximumPoolSize(1);
        hikariConfig.setConnectionTimeout(2000);

        DataSource dataSource = new HikariDataSource(hikariConfig);
        // get a connection using try with resource
        String query = "select * from employees";
/*        try(*/
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
/*                ) {*/
            System.out.println("Connection is obtained using HikariCP based datasource implementation.");

/*            try(*/
                    ResultSet rs = ps.executeQuery();
/*            ) {*/
                while(rs.next()) {
                    System.out.println("---------------Employee Details------------");
                    System.out.println("ID : " + rs.getInt("id"));
                    System.out.println("Name : " + rs.getString("name"));
                    System.out.println("Department : " + rs.getString("department"));
                    System.out.println("Salary : " + rs.getBigDecimal("salary"));
                }
/*            }*/
/*        } catch (Exception e) {
            System.out.println("Couldn't get a connection. Error Message : " + e.getMessage());
        }*/

        // again asking for a connect before closing the above one.
        String singleRowQuery = "select * from employees where id = 1";
        try(
                Connection connection2 = dataSource.getConnection();
                PreparedStatement ps2 = connection2.prepareStatement(query)
        ) {
            System.out.println("Connection is obtained 2nd time using HikariCP based datasource implementation.");

            try(ResultSet rs2 = ps2.executeQuery()) {
                if(rs2.next()) {
                    System.out.println("---------------Employee Details------------");
                    System.out.println("ID : " + rs2.getInt("id"));
                    System.out.println("Name : " + rs2.getString("name"));
                    System.out.println("Department : " + rs2.getString("department"));
                    System.out.println("Salary : " + rs2.getBigDecimal("salary"));
                }
            }
        } catch (Exception e) {
            System.out.println("Couldn't get a connection. Error Message : " + e.getMessage());
        }
    }
}
