package com.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/project_management";
		String user = "root";
		String password = "";
		Connection con = null; 
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
//			System.out.println("======= connection =======");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
