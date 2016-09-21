package com.youdy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public final class MySqlJDBC {
	
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/oneapm_si?serverTimezone=UTC";
	
	private static String name = "root";
	
	private static String password = "";
	
	private MySqlJDBC() {}
	
	private static Connection openConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, name, password);
		return conn;
	}
	
	public static ResultSet select(String sql) throws Exception {
		PreparedStatement prepareStatement = openConnection().prepareStatement(sql);
		return prepareStatement.executeQuery();
	}
}
