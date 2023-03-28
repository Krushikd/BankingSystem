

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;
	Statement stmt;
    
    public Withdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       // Input parameters
        String accountNumber = request.getParameter("accountNumber");
        String accountHolderName = request.getParameter("accountHolderName");
        double balance = Double.parseDouble(request.getParameter("balance")); 
		
		final String DB_URL ="jdbc:oracle:thin:@localhost:1521:xe"; //url for jdbc connection
		
		//database credentials
		
		final String USER = "hr";
		final String PASS ="pass";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.prepareStatement("SELECT * FROM accountdetails WHERE ACCOUNT_NUMBER = ? and ACCOUNT_HOLDER_NAME=?");
		      stmt.setString(1, accountNumber);
		      stmt.setString(2, accountHolderName);
		      rs = stmt.executeQuery();
		      
		      if (rs.next()) {
		        double bankbalance = rs.getDouble("balance");
		        if(bankbalance>=balance) {
		        	   // Update the account balance in the database
			          stmt = conn.prepareStatement("UPDATE accountdetails SET balance = ? WHERE account_number = ? and ACCOUNT_HOLDER_NAME=?");
			          stmt.setDouble(1, bankbalance - balance);
			          stmt.setString(2, accountNumber);
			          stmt.setString(3, accountHolderName);
			          stmt.executeUpdate();
			          
			          // Display a success message to the user
			       
			          response.setContentType("text/html");
			          PrintWriter out = response.getWriter();
			          out.println("<script type=\"text/javascript\">");
			          out.println("alert(' Withdraw Successful');");
			          out.println("window.location.href = 'Withdraw.jsp';"); // redirect to same page
			          out.println("</script>");	
		        }else {
		        	 // Display an error message to the user if the amount is insufficiant
			        response.setContentType("text/html");
			        PrintWriter out = response.getWriter();
			        out.println("<script type=\"text/javascript\">");
			          out.println("alert('Insufficiant Amount');");
			          out.println("window.location.href = 'Withdraw.jsp';"); // redirect to same page
			          out.println("</script>");	
		        }
		        
		       	
		       
		      } else {
		        // Display an error message to the user if the account information is invalid
		        response.setContentType("text/html");
		        PrintWriter out = response.getWriter();
		        out.println("<script type=\"text/javascript\">");
		          out.println("alert(' Withdraw Failed,Invalid account number or account holder name');");
		          out.println("window.location.href = 'Withdraw.jsp';"); // redirect to same page
		          out.println("</script>");	
		      }
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      // Close the database connection and ResultSet object
		      try { rs.close(); } catch (Exception e) {}
		      try { stmt.close(); } catch (Exception e) {}
		      try { conn.close(); } catch (Exception e) {}
		    }
		
	}//end of doPost method
}//end of class
