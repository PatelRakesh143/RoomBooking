
import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;
/**
 * Servlet implementation class HelloServlet
 */
public class Createuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated me
		response.setContentType("text/html");
		String uname=request.getParameter("uname");
		String password=request.getParameter("pass");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dreamhome","root","Raaj@1234");  
			PreparedStatement ps=con.prepareStatement("insert into signup values(?,?)");
			PrintWriter out=response.getWriter();
			ps.setString(1,uname);
			ps.setString(2, password);
			int i=ps.executeUpdate();
			if(i==0) {
				out.println("user already exist");
			}
			if(i!=0) {
				RequestDispatcher rd=request.getRequestDispatcher("admin.html");
				rd.forward(request, response);
			}
			else {
					out.println(" User Already Exits !");
					out.println("<a href=signup.html><br><br> Try Again");
			}
			con.close();

		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}

}
