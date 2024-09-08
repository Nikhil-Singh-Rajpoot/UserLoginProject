package login;

import jakarta.servlet.ServletConfig;
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
import java.sql.SQLException;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String URL="jdbc:mysql://localhost:3306/userdb";
    private static final String USERNAME="root";
    private static final String PASS="nikh@9312";
    Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			 con = DriverManager.getConnection(URL, USERNAME, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	String uName=request.getParameter("userName");
	 	String pword = request.getParameter("pword");
	 	try {
			PreparedStatement prepareStatement = con.prepareStatement("select * from uinfo where uName = ? and pword=?");
			prepareStatement.setString(1, uName);
			prepareStatement.setString(2, pword);
			ResultSet rs = prepareStatement.executeQuery();
			if(rs.next()) {
				PrintWriter writer = response.getWriter();
				writer.println("<html><body><center>");
				writer.println("<h1>Welcome </h1>"+"<h1>"+uName+"</h1>");
				writer.println("</center></body></html>");
			}else {
				PrintWriter writer = response.getWriter();
				writer.println("<html><body><center>");
				writer.println("<h1>User Not Found </h1>");
				writer.println("</center></body></html>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
