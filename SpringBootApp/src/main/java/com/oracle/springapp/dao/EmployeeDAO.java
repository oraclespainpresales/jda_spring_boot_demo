package com.oracle.springapp.dao;

import java.util.List;

import com.oracle.springapp.model.Employee;

/**
 * Simple DAO interface for EMP table.
 *
 */
public interface EmployeeDAO {
	public List<Employee> getAllEmployeesSoda(String collectionName);
	public String insertEmployeeSoda(String collectionName, Employee employee);
}
