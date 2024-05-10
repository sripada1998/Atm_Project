package ATMProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    
    public SignUp() {
        super();
        
    }

	
	public void init(ServletConfig config) 
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
		} catch (ClassNotFoundException | SQLException e) {
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
			String s1=request.getParameter("card");
			String s2=request.getParameter("pin");
			String s3=request.getParameter("amount");
			int c1=Integer.parseInt(s1);
			int c2=Integer.parseInt(s2);
			int c3=Integer.parseInt(s3);
			PreparedStatement pstmt=con.prepareStatement("insert into ATM values(?,?,?)");
			pstmt.setInt(1, c1);
			pstmt.setInt(2, c2);
			pstmt.setInt(3, c3);
			pstmt.executeUpdate();
			PrintWriter pw=response.getWriter();
			pw.println("One Record Inserted Successfully");
			RequestDispatcher rd=request.getRequestDispatcher("Retry.html");
			rd.forward(request, response);
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
