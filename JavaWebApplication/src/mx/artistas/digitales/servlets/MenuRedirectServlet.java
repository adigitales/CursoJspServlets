package mx.artistas.digitales.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/MenuRedirectServlet")
public class MenuRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}
	
	protected void procesaPeticion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pagina = request.getParameter("pagina");
		String redirectPage = null;
		HttpSession session = request.getSession();
	
		if(pagina.equals("abcUsuario")) {
			redirectPage = "abcUsuario.jsp";
		}else if(pagina.equals("registroUsuario")) {
			redirectPage = "registroUsuario.jsp";
		}else if(pagina.equals("editUsuario")) {
			redirectPage = "editUsuario.jsp";
		}
		
		session.setAttribute("page", redirectPage);
		response.sendRedirect(request.getContextPath() + "/jsp/main.jsp");
	}

}
