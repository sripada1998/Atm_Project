package ATMProject;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Withdraw
 */
public class Withdraw extends HttpServlet 
{
	Connection con;
	private static final long serialVersionUID = 1L;
  
    public Withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(ServletConfig config) throws ServletException 
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			String s=request.getParameter("pin");
			int c1=Integer.parseInt(s);
			String s1=request.getParameter("wamount");
			int c2=Integer.parseInt(s1);
			PreparedStatement pstmt=con.prepareStatement("select amount from ATM where pin=?");
			pstmt.setInt(1, c1);
			PrintWriter pw=response.getWriter();
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				int amount=rs.getInt(1);
				if(c2>amount)
				{
					pw.println("<html><body bgcolor=cyan><center><h1>");
					pw.println("Insufficient Balance<br>");
					pw.println("</h1></center></body></html>");
					RequestDispatcher rd=request.getRequestDispatcher("withdraw");
					rd.include(request, response);
				}
				else if(c2<=amount)
				{
					
					int temp=amount-c2;
					PreparedStatement pstmt1=con.prepareStatement("update ATM set amount=? where pin=?");
					pstmt1.setInt(1, temp);
					pstmt1.setInt(2, c1);
					pstmt1.execute();
					pw.println("<html><body bgcolor=cyan><center><form action=Menu.html><h1>");
					pw.println("Your Money Will be SucessFully Withdraw <br>");
					pw.println("<input type=submit name=Success<br>");
					pw.println("</h1></form></center></body></html>");
				}
			}
		} catch (NumberFormatException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
