package com.oracle.springapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oracle.soda.OracleCollection;

import com.oracle.springapp.model.Employee;
import com.oracle.springapp.service.EmployeeServiceSoda;
import com.oracle.springapp.dao.EmployeeDAO;

import com.oracle.springapp.dao.EmployeeCollectionDAO;

@Service
public class EmployeeServiceSodaImpl implements EmployeeServiceSoda {

	@Autowired
	EmployeeDAO employeeDao;
	
	@Autowired
	EmployeeCollectionDAO createCollectionDao;
	
	/**
	 * Create collection for employees
	 */

	@Override
	public OracleCollection createCollection(String name){
		return createCollectionDao.createCollection(name);
	}

	/**
	 * get collection for employees
	 */

	@Override
	public OracleCollection getCollection(String name){
		return createCollectionDao.getCollection(name);
	}
	
	/**
	 *  Displays the Employees from the EMP table 
	 */

	@Override
	public void displayEmployees(String collectionName) {
		List<Employee> employees = employeeDao.getAllEmployeesSoda(collectionName);

		System.out.println(String.format("%20s %20s %20s %20s %20s %20s %20s %20s \n", 
				"EMPNO", "ENAME", "JOB", "MGR", "HIREDATE", "SALARY", "COMM", "DEPT"));
		
		for(Employee employee: employees)
			System.out.println(employee);
	}
	
	@Override
	public void insertEmployee(String collectionName, Employee employee) {
		employeeDao.insertEmployeeSoda(collectionName, employee);	
	}
	
}
