package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class transferServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tfrom1=request.getParameter("sid");
		String tto1=request.getParameter("rid");	
		String amount1=request.getParameter("tm");
		int tfrom=Integer.parseInt(tfrom1);
		int tto=Integer.parseInt(tto1);
		int amount=Integer.parseInt(amount1);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
	    String mysqlConnUrl = "jdbc:mysql://localhost:3306/sparx";
     	String mysqlUserName = "root";
		String mysqlPassword = "Ram@9059";
		java.sql.Connection con = DriverManager.getConnection(mysqlConnUrl, mysqlUserName , mysqlPassword);
		//checking amount
		Statement selectStmt1 = con.createStatement();
		  ResultSet rs1 = selectStmt1.executeQuery("select * from customers");
		  int balance=0,tbalance=0;
		 while(rs1.next()) {
			 if(rs1.getInt(1)==tfrom) {
			  balance=rs1.getInt(4);
			 }
			 if(rs1.getInt(1)==tto) {
				  tbalance=rs1.getInt(4);
				 }
		 }
		
		if(balance>=amount) {
		//inserting records
		PreparedStatement stmt=con.prepareStatement("insert into transaction (tfrom,tto,amount) values(?,?,?)");
		stmt.setInt(1,tfrom);
		stmt.setInt(2,tto);
		stmt.setInt(3,amount);
		stmt.execute();
		
		Statement selectStmt = con.createStatement();
		  ResultSet rs = selectStmt.executeQuery("SELECT * FROM transaction WHERE tid=(SELECT max(tid) FROM transaction)");
		  while(rs.next()) {
			  RequestDispatcher rd=request.getRequestDispatcher("transfer.html");  
		rd.include(request, response);
		out.println("transaction sucess "+rs.getInt(1));
		
		}
		  int acbalance=balance-amount;
		  Statement stmt2 = con.createStatement();
		  stmt2.executeUpdate("update customers set balance='"+acbalance+"' where id='"+tfrom+"'");
		  int upbalance=tbalance+amount;
		  Statement stmt3 = con.createStatement();
		  stmt3.executeUpdate("update customers set balance='"+upbalance+"' where id='"+tto+"'");
		}
		  
		else {
			RequestDispatcher rd=request.getRequestDispatcher("transfer.html");  
			rd.include(request, response);
			out.println("transaction unsucess ");
		}
		  
		}
		catch(Exception e) {
			out.println(e);
		}
		
		
	}

}
