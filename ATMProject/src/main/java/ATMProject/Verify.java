package ATMProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

private static final long serialVersionUID = 1L;

public Verify() {
	super();

	public class Verify extends HttpServlet {
	Connection con;
    }

	public void init(ServletConfig config) throws ServletException 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void destroy() 
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			String s1=request.getParameter("Pin");
			int c2=Integer.parseInt(s1);
			String s2=request.getParameter("card");
			int c3=Integer.parseInt(s2);
			PreparedStatement pstmt=con.prepareStatement("select * from ATM where Pin=? and card=?");
			pstmt.setInt(1,c2);
			pstmt.setInt(2, c3);
			ResultSet rs=pstmt.executeQuery();
			PrintWriter pw=response.getWriter();
			if(rs.next())
			{
				RequestDispatcher rd=request.getRequestDispatcher("Menu.html");
				rd.forward(request, response);
			}
			else
			{
				pw.println("<html><body bgcolor=cyan><center><h1>");
				
				pw.println("Entered Pin Number Is Invalid");
				pw.println("</h1></center></body></html>");
				RequestDispatcher rd=request.getRequestDispatcher("SignIn.html");
				rd.include(request, response);
			}
		} catch (NumberFormatException | SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
