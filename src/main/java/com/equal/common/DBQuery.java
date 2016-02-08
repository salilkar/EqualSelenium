package com.equal.common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DBQuery {


	public void getSQLResults(String sqlQuery,String userId) throws ClassNotFoundException, SQLException{

		String url="jdbc:oracle:thin:@scan-639219.rsp.medecision.com:1521/";
		String database="MEDQA1";
		String username="AMDOQ11_ALINEO";
		String password="ALINEO";
		
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(url+database,username,password);  
			Statement stmt=con.createStatement();  
			stmt.executeQuery(""+sqlQuery+"'"+userId+"'");
			con.close(); 
		}
}




