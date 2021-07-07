package com.oracle.springapp.dao.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.oracle.springapp.dao.EmployeeCollectionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import oracle.soda.OracleCollection;
import oracle.soda.OracleDatabase;
import oracle.soda.rdbms.OracleRDBMSClient;

@Repository
public class EmployeeCollectionDAOImpl extends JdbcDaoSupport implements EmployeeCollectionDAO {
	@Autowired
    private DataSource dataSource;
	
    @PostConstruct
	public void initialize() {
		setDataSource(dataSource);
		System.out.println("Datasource used: " + dataSource);
	}

	@Override
	public OracleCollection createCollection (String name) {
        OracleRDBMSClient cl = null;
        OracleDatabase db    = null;
        OracleCollection col = null;

        try {
            cl  = new OracleRDBMSClient();
            db  = cl.getDatabase(dataSource.getConnection());
            col = db.admin().createCollection(name);
            System.out.println("Collection [" + name + "] - CREATED!!");
        }
        catch (Exception e){
            System.out.println("Error!! creating collection [" + name + "]");
            e.printStackTrace();
        }
        return col;
    }

    @Override
	public OracleCollection getCollection (String name) {
        OracleRDBMSClient cl = null;
        OracleDatabase db    = null;
        OracleCollection col = null;

        try {
            cl  = new OracleRDBMSClient();
            db  = cl.getDatabase(dataSource.getConnection());
            col = db.openCollection(name);
            System.out.println("Collection [" + name + "] - RETRIEVED!!");
        }
        catch (Exception e){
            System.out.println("Error opening collection [" + name + "]");
            e.printStackTrace();
        }
        return col;
    }
}
