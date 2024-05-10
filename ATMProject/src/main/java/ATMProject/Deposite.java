package ATMProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Deposite
 */
public class Deposite extends HttpServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException 
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=java.sql.DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			}
			catch(Exception e)
			{
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String s1=request.getParameter("newamount");
			int c2=Integer.parseInt(s1);
			String s2=request.getParameter("pin");
			int c1=Integer.parseInt(s2);
			PreparedStatement pstmt=con.prepareStatement("select amount from ATM where pin=?");
			pstmt.setInt(1, c1);
			ResultSet rs=pstmt.executeQuery();
			PrintWriter pw=response.getWriter();
			if(rs.next())
			{
				
				int a=rs.getInt(1)+c2;
				PreparedStatement pstmt1=con.prepareStatement("update ATM set amount=? where pin=?");
				pstmt1.setInt(1, a);
				pstmt1.setInt(2, c1);
				pstmt1.execute();
				
			}
			pw.println("<html><body bgcolor=cyan><center><h1><form action=Menu.html>");
			pw.println("Deposite Process Will Successfully Completed"+"<br><br>");
			pw.println("<input type=submit name=GOBACK>");
			pw.println("</form></h1></center></body></html>");
			
			
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}