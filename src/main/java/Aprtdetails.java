import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
public class Aprtdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
        // TODO Auto-generated constructor stub
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 String oname=request.getParameter("oname");
		 String type=request.getParameter("rtype");
		 String rent=request.getParameter("rrent");
		 String city=request.getParameter("city");
		 String address=request.getParameter("add");
		 String contact=request.getParameter("contact");
		 String email=request.getParameter("email");
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dreamhome","root","Raaj@1234");  
				PreparedStatement ps=con.prepareStatement("insert into aprt_details values(?,?,?,?,?,?,?)");
				PrintWriter out=response.getWriter();	
				ps.setString(1,oname);
				ps.setString(2,type);
				ps.setString(3,rent);
				ps.setString(4,city);
				ps.setString(5,address);
				ps.setString(6,contact);
				ps.setString(7,email);
				int i=ps.executeUpdate();
				if(i!=0) {
					out.println("data stored successfully");
					out.println("<button><a href=\"index.html\">Home</a></button>");
				}
				else {
						out.println(" User Already Exits !");
						out.println("<a href=signup.html><br><br> Try Again");
				}
				con.close();

}
		 catch(Exception e) {
			 System.out.println(e);
		 }

	}
}
