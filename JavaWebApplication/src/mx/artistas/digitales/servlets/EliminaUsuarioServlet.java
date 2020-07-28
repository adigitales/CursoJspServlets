package mx.artistas.digitales.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.artistas.digitales.daos.AbcUsuarioDAO;


@WebServlet("/EliminaUsuarioServlet")
public class EliminaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}
	
	protected void procesaPeticion(HttpServletRequest request, HttpServletResponse response) {
		try {
			String idUsuario = request.getParameter("idUsuario");
			HttpSession session = request.getSession();
			AbcUsuarioDAO abcDAO = new AbcUsuarioDAO();
			boolean paso = abcDAO.eliminaUsuario(Integer.parseInt(idUsuario));
			if(paso) {
				String redirectPage = (String)session.getAttribute("urlRedirectMain");
				response.sendRedirect(redirectPage + "abcUsuario");
			}else {
				//Implementar página de error
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
