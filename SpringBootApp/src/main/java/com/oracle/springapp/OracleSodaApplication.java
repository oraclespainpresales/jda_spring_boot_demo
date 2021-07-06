package com.oracle.springapp;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.soda.OracleDatabase;
import oracle.soda.OracleException;
import oracle.soda.rdbms.OracleRDBMSClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oracle.springapp.model.Employee;
import com.oracle.springapp.service.EmployeeService;

public class OracleSodaApplication implements CommandLineRunner{
    public static void main(String[] args) {
		SpringApplication.run(OracleSodaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        /*
        OracleRDBMSClient cl = new OracleRDBMSClient();
        OracleDatabase db    = cl.getDatabase(conn);
        OracleCollection col = db.admin().createCollection("MyJSONCollection");

        // Create a JSON document.
        OracleDocument doc = db.createDocumentFromString("{ \"name\" : \"Borja\" }");

        // Insert the document into a collection, and get back its
        // auto-generated key.
        System.out.println("Before inserting document");
        String k = col.insertAndGet(doc).getKey();

        // Find a document by its key.
        System.out.println("Inserted content:" + col.find().key(k).getOne().getContentAsString() + "with Key: "+ k);

        OracleDocument f = db.createDocumentFromString("{\"name\" : { \"$startsWith\" : \"B\" }}");
        OracleCursor c   = col.find().filter(f).getCursor();

        while (c.hasNext()) {
            // Get the next document.
            OracleDocument resultDoc = c.next();

            // Print the document key and content.
            System.out.println("Key:         " + resultDoc.getKey());
            System.out.println("Content:     " + resultDoc.getContentAsString());
        }
        */
    }
}