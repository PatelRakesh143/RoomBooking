import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.*;
public class ViewuserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
        // TODO Auto-generated constructor stub
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		 //out.println("<html><body>");
		 String str=request.getParameter("in");
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dreamhome","root","Raaj@1234");  
			
		 try {
			 	String query="select *from signup where uname =?";
				PreparedStatement pstmt=con.prepareStatement(query);
				pstmt.setString(1,str);
				ResultSet rs=pstmt.executeQuery();
				int i=1;
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
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					String email=rs.getString("uname");
					String pass=rs.getString("password");
					out.println(i+":-  User Details:-");
					out.println("<br>");
					out.println("Email Address:-  "+email);
					out.println("<br>");
					out.println("Password:-  "+pass);
					String data = email;
					HttpSession session = request.getSession();
					session.setAttribute("data", data);
					out.println("<!DOCTYPE html>\r\n"
							+ "<html lang=\"en\">\r\n"
							+ "<head>\r\n"
							+ "    <meta charset=\"UTF-8\">\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "    <title>Document</title>\r\n"
							+ "    <link rel=\"stylesheet\" href=\"userd.css\">\r\n"
							+ "</head>\r\n"
							+ "<body>\r\n"
							+ "    <div class=\"box\">\r\n"
							+ "        <form action=\"Roomd\" method=\"post\">\r\n"
							+ "            <input type=\"submit\" id=\"img\" value=\"Room Details\">\r\n"
							+ "        </form>\r\n"
							+ "        <form action=\"Delete\" method=\"post\">\r\n"
							+ "            <input type=\"submit\" id=\"del\" value=\"Delete\">\r\n"
							+ "        </form>    \r\n"
							+ "    </div>\r\n"
							+ "</body>\r\n"
							+ "</html>");
					out.println("<br>");

				}
				
				if(!flag) {
					out.println("No Data Found");
				}
				rs.close();

				}
		 catch(Exception e) {
			 System.out.println(e);
		 }
		 finally{
			 if(con!=null)
				 con.close();
				
		 }
		 }
		 catch(Exception e) {e.printStackTrace();}

	}
}
