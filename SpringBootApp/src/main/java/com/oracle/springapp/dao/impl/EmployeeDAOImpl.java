package com.oracle.springapp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import oracle.soda.OracleCollection;
import oracle.soda.OracleCursor;
import oracle.soda.OracleDatabase;
import oracle.soda.OracleDocument;
import oracle.soda.rdbms.OracleRDBMSClient;

import com.oracle.springapp.dao.EmployeeDAO;
import com.oracle.springapp.model.Employee;

/**
 * Simple Java class which uses Spring's JdbcDaoSupport class to implement
 * business logic.
 *
 */
@Repository
public class EmployeeDAOImpl extends JdbcDaoSupport implements EmployeeDAO {
	
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void initialize() {
		setDataSource(dataSource);
		System.out.println("Datasource used: " + dataSource);
	}

	@Override
	public List<Employee> getAllEmployeesSoda(String collectionName) {
		OracleRDBMSClient cl  = null;
        OracleDatabase db     = null;
        OracleCollection coll = null;
		List<Employee> employees = new ArrayList<Employee>();

        try {
            cl   = new OracleRDBMSClient();
            db   = cl.getDatabase(dataSource.getConnection());
            coll = db.openCollection(collectionName);
            System.out.println("Collection [" + collectionName + "] - RETRIEVED!!");

			OracleCursor c = coll.find().getCursor();

			while (c.hasNext()) {
				employees.add(new Employee(c.next()));
			}
        }
        catch (Exception e){
            System.out.println("Error getting employees!");
            e.printStackTrace();
        }
		return employees;
	}
	
	@Override
	public String insertEmployeeSoda(String collectionName, Employee employee) {
		OracleRDBMSClient cl  = null;
        OracleDatabase db     = null;
        OracleCollection coll = null;
		String result         = "";

        try {
            cl   = new OracleRDBMSClient();
            db   = cl.getDatabase(dataSource.getConnection());
            coll = db.openCollection(collectionName);
            System.out.println("Collection [" + collectionName + "] - RETRIEVED!!");

			OracleDocument doc = db.createDocumentFromString(employee.toJSON());
			OracleDocument insertedDoc = coll.insertAndGet(doc);			
			System.out.println("Collection [" + collectionName + "] - Employee [" + employee.getName() + "] Inserted!! with key ["+insertedDoc.getKey()+"]");
			result = insertedDoc.getKey();
        }
        catch (Exception e){
            System.out.println("Error inserting EMP [" + employee.getName() + "]");
            e.printStackTrace();
        }
		return result;
	}
}
