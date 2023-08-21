

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Bookroom
 */
public class Bookroom extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");

		String aadhar=request.getParameter("aadhar");
		String name=request.getParameter("name");
		String mobile=request.getParameter("mobile");
		String checkin=request.getParameter("checkin");
		String checkout=request.getParameter("checkout");
		try {
			HttpSession session = request.getSession();
		    int  data = (int) session.getAttribute("data");
		    String email=(String) session.getAttribute("email");
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dreamhome","root","Raaj@1234");  
			PreparedStatement ps=con.prepareStatement("insert into bookingdetails values(?,?,?,?,?,?,?)");
			PrintWriter out=response.getWriter();
			ps.setInt(1, data);
			ps.setString(2,aadhar);
			ps.setString(3, name);
			ps.setString(4, mobile);
			ps.setString(5, checkin);
			ps.setString(6, checkout);
			ps.setString(7, email);
			int i=ps.executeUpdate();
			if(i!=0) {
				out.println("Room Booked <br>THANKYOU !");
				//RequestDispatcher rd=request.getRequestDispatcher("login.html");
				//rd.forward(request, response);
			}
			
			con.close();

		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}

}
