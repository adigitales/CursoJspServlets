package mx.artistas.digitales.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.artistas.digitales.beans.UsuarioBean;
import mx.artistas.digitales.daos.LoginDAO;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}

	protected void procesaPeticion(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			request.setCharacterEncoding("UTF-8");
			String correo 	= request.getParameter("usuario");
			String pass 	= request.getParameter("pass");
			LoginDAO loginDAO = new LoginDAO();
			UsuarioBean usuarioBean  = loginDAO.getuserLogin(correo, pass);
			if(usuarioBean != null) {
				//response.sendRedirect(request.getContextPath()+ "/html/formulario.html" );
				String urlPage  = request.getContextPath() + "/MenuRedirectServlet?pagina=";
				HttpSession session = request.getSession();
				session.setAttribute("usuarioBean", usuarioBean);
				session.setAttribute("urlRedirectMain", urlPage);
				response.sendRedirect(request.getContextPath()+ "/jsp/main.jsp" );
			}else {
				out.println("<h1> Usuario y/o Contraseña no son correctos");
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
}
