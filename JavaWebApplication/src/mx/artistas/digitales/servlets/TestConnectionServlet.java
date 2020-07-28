package mx.artistas.digitales.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.artistas.digitales.db.ConnectionWrapper;

/**
 * Servlet implementation class TestConnectionServlet
 */
@WebServlet("/TestConnectionServlet")
public class TestConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
	
	public TestConnectionServlet() {
		ConnectionWrapper conectionWraper = new ConnectionWrapper();
		try {
			con = conectionWraper.getConection();
			if(con !=null) {
				System.out.println("¡Conexión Exitosa!");
			}else {
				System.out.println("¡Conexión Nula!");
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conectionWraper.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
