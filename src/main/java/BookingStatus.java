

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
import java.sql.ResultSet;

/**
 * Servlet implementation class BookingStatus
 */
public class BookingStatus extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 PrintWriter out=response.getWriter();
		 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dreamhome","root","Raaj@1234");
		 try {
			 HttpSession session = request.getSession();
		        int data  = (int) session.getAttribute("data");
				String query="select aadhar ,name, mobile,checkin, checkout from bookingdetails where id=?";
				PreparedStatement pstmt=con.prepareStatement(query);
				pstmt.setInt(1,data);
				ResultSet rs=pstmt.executeQuery();
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
					//System.out.println("this is working"+rs.getString("owner"));
					String aadhar =rs.getString("aadhar");
					String name=rs.getString("name");
					String mobile =rs.getString("mobile");
					String checkin=rs.getString("checkin");
					String checkout=rs.getString("checkout");
					out.print("Customer Details:-");
					out.println("<br>");
					out.println("Aadhar:-  "+aadhar);
					out.println("<br>");
					out.println("Name:-  "+name);
					out.println("<br>");
					out.println("Contact:-  "+mobile);
					out.println("<br>");
					out.println("Checkin Date:-  "+checkin);
					out.println("<br>");
					out.println("CheckOut Date:-  "+checkout);
					out.println("<br>");
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
