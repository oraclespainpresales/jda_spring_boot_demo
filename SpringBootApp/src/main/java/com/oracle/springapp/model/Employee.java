package com.oracle.springapp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import oracle.soda.OracleDocument;

import org.json.*;


/**
 * Simple model for EMP table.
 *
 */
public class Employee {
	private Integer empno;
	private String name;
	private String job;
	private Integer manager;
	private LocalDateTime hiredate;	
	private Integer salary;
	private Integer commission;
	private Integer deptno;
	
	public Employee(int _empno,
			String _name,
			String _job,
			int _mgr,
			LocalDateTime date,
			int _sal,
			int _commission,
			int _deptno) {
		empno      = _empno;
		name       = _name;
		job        = _job;
		manager    = _mgr;
		hiredate   = date;
		salary     = _sal;
		commission = _commission;
		deptno     = _deptno;
	}

	public Employee(OracleDocument jsonDoc) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		try {
			JSONObject jsonObj    = new JSONObject(jsonDoc.getContentAsString());
			
			empno      = jsonObj.getInt("empno");
			name       = jsonObj.getString("name");
			job        = jsonObj.getString("job");
			manager    = jsonObj.getInt("manager");
			hiredate   = LocalDateTime.parse(jsonObj.getString("hiredate"), dtf);
			salary     = jsonObj.getInt("salary");
			commission = jsonObj.getInt("commission");
			deptno     = jsonObj.getInt("deptno");
		}
		catch (Exception e){
			System.out.println ("Error parsing employee");
			e.printStackTrace();
		}
	}

	public Employee(JSONObject jsonObj) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		try {		
			empno      = jsonObj.getInt("empno");
			name       = jsonObj.getString("name");
			job        = jsonObj.getString("job");
			manager    = jsonObj.getInt("manager");
			hiredate   = LocalDateTime.parse(jsonObj.getString("hiredate"), dtf);
			salary     = jsonObj.getInt("salary");
			commission = jsonObj.getInt("commission");
			deptno     = jsonObj.getInt("deptno");
		}
		catch (Exception e){
			System.out.println ("Error parsing employee");
			e.printStackTrace();
		}
	}


	public Integer getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getManager() {
		return manager;
	}

	public void setManager(int mgr) {
		this.manager = mgr;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(int sal) {
		this.salary = sal;
	}

	public Integer getDeptno() {
		return deptno;
	}
	
	public Integer getCommission() {
		return commission;
		
	}
	
	public LocalDateTime getHiredate() {
		return hiredate;
	}
	
	public void setHiredate(Timestamp hiredate) {
		this.setHiredate(hiredate);
	}
	
	public void setCommission(int commission) {
		this.commission=commission;
		
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String toString() {
		return String.format("%20s %20s %20s %20s %20s %20s %20s %20s", empno, name,
				job, manager, hiredate, salary, commission, deptno);

	}

	public String toJSON(){
		//format "{ \"name\" : \"Alexander\" }"
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

		StringBuilder str = new StringBuilder("{");
		str.append("\"empno\" : \"").append(empno).append("\",");
		str.append("\"name\" : \"").append(name).append("\",");
		str.append("\"job\" : \"").append(job).append("\",");
		str.append("\"manager\" : \"").append(manager).append("\",");
		str.append("\"hiredate\" : \"").append(hiredate.format(dtf)).append("\",");
		str.append("\"salary\" : \"").append(salary).append("\",");
		str.append("\"commission\" : \"").append(commission).append("\",");
		str.append("\"deptno\" : \"").append(deptno).append("\"");	
		str.append("}");

		System.out.println("EMP: " + str.toString());

		return str.toString();
	}
}
