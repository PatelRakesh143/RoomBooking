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
public class Userdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
        // TODO Auto-generated constructor stub
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dreamhome","root","Raaj@1234");  
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select*from signup");
				out.println("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>Document</title>\r\n"
						+ "    <link rel=\"stylesheet\" href=\"fatchdata.css\">\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+							"\r\n"

						+ "    <div class=\"search\">\r\n"
						+ "        <form action=\"ViewuserDetails\" method=\"post\">\r\n"
						+ "        <input type=\"text\" placeholder=\"Enter Email\" name=\"in\">\r\n"
						+ "        <input type=\"submit\" value=\"Search\" id=\"sub\">\r\n"
						+ "    </form>\r\n"
						+ "    </div>\r\n"
						+ "</body>\r\n"
						+ "</html>");
				int i=1;
				while(rs.next()) {
					String email=rs.getString("uname");
					String pass=rs.getString("password");
					out.println(i+":-  User Details:-");
					out.println("<br>");
					out.println("Email Address:-  "+email);
					out.println("<br>");
					out.println("Password:-  "+pass);
					out.println("<br><br><br>");
					i++;
					
				}
				con.close();
				rs.close();
	}
		 catch(Exception e) {
			 System.out.println(e);
		 }

	}
}
