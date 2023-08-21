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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
public class ViewImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
        // TODO Auto-generated constructor stub
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OutputStream outputStream = response.getOutputStream(); 
		try {
		 Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dreamhome","root","Raaj@1234");
		 try {
		        HttpSession session = request.getSession();
		        int  data = (int) session.getAttribute("data");
				String query="select image from room where id= ?";
				PreparedStatement pstmt=con.prepareStatement(query);
				pstmt.setInt(1,data);
				ResultSet rs=pstmt.executeQuery();
				boolean flag=false;
				while(rs.next()) {
					flag=true;
					response.setContentType("image/jpeg"); 
	                byte[] imageBytes = rs.getBytes("image");
	               outputStream.write(imageBytes);
	              
				}
				if(!flag) {
					String str ="No Image Found";
					outputStream.write(str.getBytes());
				}
				 outputStream.close();
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
