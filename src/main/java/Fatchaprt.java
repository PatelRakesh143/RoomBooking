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
public class Fatchaprt extends HttpServlet {
	private static final long serialVersionUID = 1L;
        // TODO Auto-generated constructor stub
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dreamhome","root","Raaj@1234");  
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select*from aprt_details");
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
						+ "    <div class=\"search\">\r\n"
						+ "        <form action=\"Searchaprt\" method=\"post\">\r\n"
						+ "        <input type=\"text\" placeholder=\"Enter City Name\" name=\"in\">\r\n"
						+ "        <input type=\"submit\" value=\"Search\" id=\"sub\">\r\n"
						+ "    </form>\r\n"
						+ "    </div>\r\n"
						+ "</body>\r\n"
						+ "</html>");
				while(rs.next()) {
					String owner=rs.getString("owner");
					String type=rs.getString("type");
					String rent=rs.getString("rent");
					String city=rs.getString("city");
					String address=rs.getString("address");
					String contact=rs.getString("contact");
					out.println("Rooms Details:-");
					out.println("<button><a href=\"\">View Image</a></button>");
					out.println("<br>");
					out.println("Owner Name:-  "+owner);
					out.println("<br>");
					out.println("Type:-  "+type);
					out.println("<br>");
					out.println("Rent:-  "+rent);
					out.println("<br>");
					out.println("City:-  "+city);
					out.println("<br>");
					out.println("Address:-  "+address);
					out.println("<br>");
					out.println("Conatct:-  "+contact);
					out.println("<br><br>");
				}
				con.close();

}
		 catch(Exception e) {
			 System.out.println(e);
		 }

	}
}
