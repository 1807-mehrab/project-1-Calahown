package com.revature;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream("C:/Users/David/Documents/workspace-sts-3.9.5.RELEASE/Hotel/connection.properties");
		prop.load(in);
		
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String password = prop.getProperty("password");
		
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		in.close();
		return DriverManager.getConnection(url, user, password);
	}
}