package com.oracle.springapp.service;

import com.oracle.springapp.model.Employee;

import oracle.soda.OracleCollection;

public interface EmployeeServiceSoda {
	/**
	 * Create collection for employees
	 */

	public OracleCollection createCollection(String name);

	/**
	 * get collection for employees
	 */

	public OracleCollection getCollection(String name);
	
	/**
	 * Display all employees.
	 */
	public void displayEmployees(String collectionName);

	/**
	 * Display all employees JSON format.
	 */
	public String displayEmployeesJson(String collectionName);
	
	/**
	 * Create a new employee record
	 */
	
	public String insertEmployee(String collectionName,Employee employee);
}
