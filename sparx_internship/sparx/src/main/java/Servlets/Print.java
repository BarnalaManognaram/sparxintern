package Servlets;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Print {
 public static void main(String[] args)throws Exception {
	 Class.forName("com.mysql.jdbc.Driver");
	    String mysqlConnUrl = "jdbc:mysql://localhost:3306/sparx";
  	String mysqlUserName = "root";
		String mysqlPassword = "Ram@9059";
		java.sql.Connection con = DriverManager.getConnection(mysqlConnUrl, mysqlUserName , mysqlPassword);
		Statement selectStmt = con.createStatement();
		  ResultSet rs = selectStmt.executeQuery("SELECT * FROM customers");
		  while(rs.next()) {
			  int id=rs.getInt(1);
			  String name=rs.getString(2);
			  String email=rs.getString(3);
			  long balance=rs.getLong(4);
			  System.out.println(id+" "+name+" "+email+" "+balance);
		  }
 }
}
