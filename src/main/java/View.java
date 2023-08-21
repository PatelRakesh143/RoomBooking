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
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
        // TODO Auto-generated constructor stub
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dreamhome","root","Raaj@1234");
				Statement stmt = con.createStatement();
				String query="select * from room";	
				out.println("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
						+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						+ "    <title>search room</title>\r\n"
						+ "    <link rel=\"stylesheet\" href=\"fatchdata.css\">\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "</body>\r\n"
						+ "</html>");
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()) {
					String email=rs.getString("email");
					String owner=rs.getString("owner");
					String type=rs.getString("type");
					String rent=rs.getString("rent");
					String city=rs.getString("city");
					String address=rs.getString("address");
					String contact=rs.getString("contact");
					out.println("Rooms Details:-");out.print("<!DOCTYPE html>\r\n"
							+ "<html lang=\"en\">\r\n"
							+ "<head>\r\n"
							+ "    <meta charset=\"UTF-8\">\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "    <title>Document</title>\r\n"
							+ "    <link rel=\"stylesheet\" href=\"userd.css\">\r\n"
							+ "</head>\r\n"
							+ "<body>\r\n"
							+ "    <div class=\"box\">\r\n"
							+ "        <form action=\"ViewImage\" method=\"post\">\r\n"
							+ "            <input type=\"submit\" id=\"img\" value=\"Images\">\r\n"
							+ "        </form>\r\n"
							+ "    </div>\r\n"
							+ "</body>\r\n"
							+ "</html>");
					
					out.println("<br>");
					out.println("Email:-  "+email);
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
				stmt.close();
				con.close();
				out.close();

}
		 catch(Exception e) {
			 System.out.println(e);
		 }

	}
}
