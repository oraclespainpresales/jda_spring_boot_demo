package com.oracle.springapp.dao;

import oracle.soda.OracleCollection;

/**
 * Simple DAO interface for Creating collection.
 *
 */
public interface EmployeeCollectionDAO {
	public OracleCollection createCollection (String name);
	public OracleCollection getCollection (String name);
}