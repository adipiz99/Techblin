package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;

@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action= request.getParameter("action");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(action.equals("provincia")) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				
				String selectSQL = "SELECT reg_ID FROM regione WHERE reg_nome=?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setString(1, request.getParameter("regione"));
				ResultSet rs = preparedStatement.executeQuery();
				int id=0;
				while(rs.next()) {
					id= rs.getInt("reg_ID");
				}
				
				selectSQL = "SELECT prov_nome FROM provincia WHERE reg_ID=?";
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, id);
				System.out.println("Query AJAX: " + preparedStatement);
				
				rs = preparedStatement.executeQuery();
				String toSend="[ ";
				int count=0;
				while(rs.next()) {
					if(count != 0) toSend += ", ";
					toSend += "\"" + rs.getString("prov_nome") + "\"";
					count++;
				}
				toSend+= " ]";
				response.setHeader("result", toSend);
				response.getWriter().write(toSend);
			}
			catch(SQLException e) {}
		}
		else if(action.equals("regione")) {
			try {
				connection = DriverManagerConnectionPool.getConnection();
				String selectSQL = "SELECT reg_nome FROM regione";
				preparedStatement = connection.prepareStatement(selectSQL);
				System.out.println("Query AJAX: " + selectSQL);
				
				ResultSet rs = preparedStatement.executeQuery();
				String toSend="[ ";
				int count=0;
				while(rs.next()) {
					if(count != 0) toSend += ", ";
					toSend += "\"" + rs.getString("reg_nome") + "\"";
					count++;
				}
				toSend+= " ]";
				response.setHeader("result", toSend);
				response.getWriter().write(toSend);
			}
			catch(SQLException e) {}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
