package com.oracle.springapp;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import oracle.soda.OracleCollection;

import com.oracle.springapp.model.Employee;
import com.oracle.springapp.service.EmployeeServiceSoda;

/**
 * SpringBoot application main class. It uses JdbcTemplate class which
 * internally uses UCP for connection check-outs and check-ins.
 *
 */
@SpringBootApplication
public class OracleSodaApplication implements CommandLineRunner {
	@Autowired
    EmployeeServiceSoda employeeServiceSoda;
    
	public static void main(String[] args) {
		SpringApplication.run(OracleSodaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String collName = "Employee";

		OracleCollection coll = employeeServiceSoda.getCollection(collName);
		// if collection Employee is not created, it would be created.
		if (coll == null){
			coll = employeeServiceSoda.createCollection(collName);
		}

		//Insert new employee
		//Employee employee = new Employee (100,"Borja","BDM",10,LocalDateTime.now(),90000,35000,5);		
		StringBuilder str = new StringBuilder("{");
		str.append("\"empno\" : \"100\",");
		str.append("\"name\" : \"Borja\",");
		str.append("\"job\" : \"BDM\",");
		str.append("\"manager\" : \"10\",");
		str.append("\"hiredate\" : \"2021-07-07T13:41:57Z\",");
		str.append("\"salary\" : \"90000\",");
		str.append("\"commission\" : \"35000\",");
		str.append("\"deptno\" : \"5\"");
		str.append("}");
		JSONObject jsonObj = new JSONObject(str.toString());
		Employee employee  = new Employee (jsonObj);
		//employeeServiceSoda.insertEmployee(collName, employee);

		//Get employee
		employeeServiceSoda.displayEmployees(collName);
	}
}
