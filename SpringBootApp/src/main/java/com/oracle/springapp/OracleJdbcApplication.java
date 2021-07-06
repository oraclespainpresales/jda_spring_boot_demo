package com.oracle.springapp;


//import java.sql.Date;
import java.sql.*;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.oracle.springapp.model.Employee;
//import com.oracle.springapp.service.EmployeeService;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

/**
 * SpringBoot application main class. It uses JdbcTemplate class which
 * internally uses UCP for connection check-outs and check-ins.
 *
 */
//@SpringBootApplication
public class OracleJdbcApplication implements CommandLineRunner {
	PoolDataSource poolDataSource = null;
	//  public static final String DB_URL="jdbc:oracle:thin:@winedemo_medium?TNS_ADMIN=/Users/bogomez/development/mywallet";
	//String DB_URL="jdbc:oracle:thin:@winedemo_medium?TNS_ADMIN=/workspace/jda__spring_boot_demo/SpringBootApp/src/main/resources/wallet";
	String DB_URL="jdbc:oracle:thin:@jdademoemp_medium?TNS_ADMIN=/workspace/jda__spring_boot_demo/SpringBootApp/src/main/resources/wallet";
	String DB_USER = "admin";
	//public String DB_PASSWORD = null;
	String DB_PASSWORD = "Autonomous#2021" ;	
	String CONN_FACTORY_CLASS_NAME="oracle.jdbc.pool.OracleDataSource";

    //@Autowired
    //EmployeeService employeeService;
    
	public static void main(String[] args) {
		SpringApplication.run(OracleJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		poolDataSource = PoolDataSourceFactory.getPoolDataSource();
        try {
            poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
            poolDataSource.setURL(DB_URL);
            poolDataSource.setUser(DB_USER);
            poolDataSource.setPassword(DB_PASSWORD);
            poolDataSource.setConnectionPoolName("JDBC_UCP_POOL");
			System.out.println(DB_URL);
        }
        catch (final SQLException e) {
            System.out.println("Pool data source error!");
            e.printStackTrace();
        }
        System.err.println("Pool data source setup...");

		String queryStatement = "select employeeid, employeename from EMP";
		System.out.println("\n Query is " + queryStatement);
		
		try (Connection conn = poolDataSource.getConnection()) {
			conn.setAutoCommit(false);
			// Prepare a statement to execute the SQL Queries.
			try (Statement statement = conn.createStatement(); 
			// Select 20 rows from the CUSTOMERS table from SH schema. 
			ResultSet resultSet = statement.executeQuery(queryStatement)) {
		
				System.out.println("EMP ID" + "  " + "EMP NAME");
				System.out.println("---------------------");
		
				while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
				}
			System.out.println("\nCongratulations! You have successfully used Oracle Autonomous Database\n");
			}
		}
		catch(Exception e){
			System.out.println("ERROR GORDOOOO!!!! Pool data source error!");
			e.printStackTrace();
		}
		/*
		// Displays 20 table names from ALL_TABLES
		employeeService.displayTableNames();
		System.out.println("List of employees");
		// Displays the list of employees from EMP table
		employeeService.displayEmployees();
		// Insert a new employee into EMP table
		employeeService.insertEmployee(new Employee(7954,"TAYLOR","MANAGER",7839, Date.valueOf("2020-03-20"),5300,0,10));
		System.out.println("List of Employees after the update");
		// Displays the list of employees after a new employee record is inserted
		employeeService.displayEmployees();		
		*/
	}

}
