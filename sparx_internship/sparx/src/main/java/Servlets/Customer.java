package Servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.Exception;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Customer
 */
public class Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
		// TODO Auto-generated method stub
		
			 Class.forName("com.mysql.jdbc.Driver");
			    String mysqlConnUrl = "jdbc:mysql://localhost:3306/sparx";
		  	String mysqlUserName = "root";
				String mysqlPassword = "Ram@9059";
				java.sql.Connection con = DriverManager.getConnection(mysqlConnUrl, mysqlUserName , mysqlPassword);
				Statement selectStmt = con.createStatement();
				  ResultSet rs = selectStmt.executeQuery("SELECT * FROM customers");
	out.println("<html>");
	out.println("<body>");
	out.println("<a href=\"home.html\"><img src=\"bmr Logo.png\" height=\"100px\"/></a>\r\n"
			+ "   <marquee class=\"heading\"><h2>WELCOME TO BMR BANK A BEST WAY OF BANKING</h2></marquee>\r\n"
			+ "    <a href=\"Customer\"><button class=\"button1\">view all customers</button></a> <a href=\"transfer.html\"><button class=\"button1\">transfer money</button></a><br><br>\r\n"
			+"<br><br>\r\n"
									+ "");
	out.println("<center>");
	out.println(" <a href=\"transfer.html\">");
	out.println("<table>");
	out.println("<link rel=\"stylesheet\" href=\"home.css\">");
	out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">");
	out.println("<h2>Customers</h2>");
	out.println("<tr><td>"+"<b>Id</b>"+"</td><td> "+"<b>Name</b>"+"</td><td> "+"<b>Email</b>"+" </td><td>"+"<b>Current Balance</b>"+"</td></tr>");
		  while(rs.next()) {
			  int id=rs.getInt(1);
			  String name=rs.getString(2);
			  String email=rs.getString(3);
			  long balance=rs.getLong(4);
			  out.println("<tr><td>"+id+"</td><td> "+name+"</td><td> "+email+" </td><td>"+balance+"</td></tr>");
		  }
		 
		
		
		}
		catch(Exception e){
			out.println("Failed"+e);
		}
		
		out.println("</table>");
		out.println("</a>");
		
		out.println("<style>");
		out.println("*{"
				+"text-decoration:none;"+"}");
		out.println("</style>");
		
		out.println("</center>");
		out.println( "   <div class=\"bottom\">\r\n"
				+ "     <h1>CONTACT US:</h1>\r\n"
				+ "     &#128231;<p1><b>EMAIL :</b> bmrbank@gmail.com</p1><br><br>\r\n"
				+ "<img src=\"1024px-Branch_office_symbol.svg.png\" height=\"25px\"/><p1><b>OFFICE:</b> Bandar road, Vijayawada.</p1><br><br>\r\n"
				+ "     <p1><b>follow us on:</b></p1><br>\r\n"
				+ "     <a class=\"fa fa-facebook\"></a>&nbsp; &nbsp; \r\n"
				+ "     <a class=\"fa fa-instagram\"></a>&nbsp; &nbsp; \r\n"
				+ "<a  class=\"fa fa-twitter\"></a> \r\n"
				+ "   </div>\r\n"
);
		
		out.println("<style>\r\n"
				+ "table {\r\n"
				+ "  font-family: arial, sans-serif;\r\n"
				+ "  border-collapse: collapse;\r\n"
				+ "  width: 100%;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "td {\r\n"
				+ "  border: 1px solid #dddddd;\r\n"
				+ "  text-align: left;\r\n"
				+ "  padding: 8px;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "tr:nth-child(even) {\r\n"
				+ "  background-color: #dddddd;\r\n"
				+ "}\r\n"
				+ "</style>");
		out.println("</body>");
		out.println("</html>");
		out.close();
		
	}

}
