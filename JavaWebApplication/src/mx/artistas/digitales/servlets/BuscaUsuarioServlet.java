package mx.artistas.digitales.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.artistas.digitales.beans.UsuarioBean;
import mx.artistas.digitales.daos.AbcUsuarioDAO;


@WebServlet("/BuscaUsuarioServlet")
public class BuscaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}
	
	protected void procesaPeticion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nomBuscar = request.getParameter("nomBuscar");
		HttpSession session = request.getSession();
		session.setAttribute("nomBuscar", nomBuscar);
		String redirectPage = (String)session.getAttribute("urlRedirectMain");
		response.sendRedirect(redirectPage + "abcUsuario");
	}

}
