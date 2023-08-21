

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
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dreamhome","root","Raaj@1234");  
			String u=request.getParameter("uname");
			String p=request.getParameter("pass");
			PreparedStatement ps=con.prepareStatement("select uname from signup where uname=? and password=?");
			ps.setString(1, u);
			ps.setString(2, p);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("uploadp.html");
				out.print("Log-in successfully!");
				rd.forward(request, response);
			}
			else {

				out.println("Login Failed<br>Please Check Email and Password");
				out.println("<a href=login.html><br><br> Try Again</a>");
			
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		
	}

}
