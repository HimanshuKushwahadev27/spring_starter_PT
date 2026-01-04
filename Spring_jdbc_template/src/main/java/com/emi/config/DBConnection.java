package com.emi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class DBConnection {
  
     Connection con=null;
	public  Connection getConnectedWithPg() {
		try {
			
			// Load the PostgreSQL JDBC driver dynamically
			Class.forName("org.postgresql.Driver");
			
			//to make connection with the driver we will need URL
			String url="jdbc:postgresql://localhost:5433/mydatabase";
			
			//establish the connection
			//CREATE USER app_user WITH PASSWORD 'app_pass';
			//GRANT ALL PRIVILEGES ON DATABASE "database name" TO app_user;
			//it skips the need of username and password in the method

			con=DriverManager.getConnection(url,"postgres","DEfaulty");
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
