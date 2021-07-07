package com.oracle.springapp;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import oracle.soda.OracleCollection;

import com.oracle.springapp.model.Employee;
import com.oracle.springapp.service.EmployeeServiceSoda;

/**
 * SpringBoot application main class. It uses JdbcTemplate class which
 * internally uses UCP for connection check-outs and check-ins.
 *
 */
@RestController
public class OracleSodaApplicationWebController {
	@Autowired
    EmployeeServiceSoda employeeServiceSoda;

	//Collection Name.
	private static String collName = "Employee";

	//Create new Collection new employee
    @GetMapping("/initialize")
    public void initializeCollection(){		
		OracleCollection coll = employeeServiceSoda.getCollection(collName);
		// if collection Employee is not created, it would be created.
		if (coll == null){
			coll = employeeServiceSoda.createCollection(collName);
		}
	}

	//Insert new employee
	@PostMapping(path = "/insert", consumes = "application/json", produces = "application/json")
	//@PostMapping(path = "/insert", consumes = "application/json", produces = "application/json")
	public String insertEmployeeSoda (@RequestBody String emp) {
		JSONObject result = new JSONObject("{}");

		if (emp != null && !emp.equals("")) {
			System.out.println("EMP " + emp);
			JSONObject jsonObj = new JSONObject(emp);
			Employee employee  = new Employee (jsonObj);
			result.put("key","EMP [" + employeeServiceSoda.insertEmployee(collName, employee) + "]");
			result.put("result","EMP [" + employee.getName() + "] ADDED!");
		}
		else {
			result.put("result","EMP null!!");
		}
		return result.toString();
	}
	//Get employees
	@GetMapping("/display")	
	public String displayEmployees (){	
		return employeeServiceSoda.displayEmployeesJson(collName);	
	}
}
