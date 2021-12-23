package com.musalasoft.drone.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	 public static Connection connect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, Exception {
	        Connection conn = null;
	        String host = "";
	        String passwords = "";
	        String Instance = "";
	        String usernames = "";
	        ////////temp//////
	        host = "localhost";
	        usernames = "rtuser";
	        passwords = "rtuatdb123$";
	        Instance = "rtdb1pdb";
	        //ubslive
	        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
	        System.out.println("Connecting to the database..." + host + " : ---- : " + usernames);
	        //conn = DriverManager.getConnection(host, usernames, (passwords));
	        conn = DriverManager.getConnection(
	                "jdbc:oracle:thin:@" + host + ":1521/" + Instance + "", usernames, passwords);
	        System.out.println("Connected to the database");
	        return conn;
	    }

}
