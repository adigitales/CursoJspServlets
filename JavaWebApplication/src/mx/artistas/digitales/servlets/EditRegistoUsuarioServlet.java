package mx.artistas.digitales.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.artistas.digitales.beans.UsuarioBean;


@WebServlet("/EditRegistoUsuarioServlet")
public class EditRegistoUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditRegistoUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}
	
	protected void procesaPeticion(HttpServletRequest request, HttpServletResponse response) {

		try {
			String nomCompleto = request.getParameter("nomCompleto");
			String correo = request.getParameter("correo");
			String sexo = request.getParameter("sexo");
			String pais = request.getParameter("pais");
			String idRol = request.getParameter("idRol");
			String idUsuario = request.getParameter("idUsuario");
			UsuarioBean usuario = new UsuarioBean();
			usuario.setNomCompleto(nomCompleto);
			usuario.setCorreo(correo);
			usuario.setSexo(sexo);
			usuario.setPais(pais);
			usuario.setIdRol(Integer.parseInt(idRol));
			usuario.setIdUsuario(Integer.parseInt(idUsuario));
			HttpSession sesion = request.getSession();
			sesion.setAttribute("editUsuarioBean", usuario);
			String urlRedirect = sesion.getAttribute("urlRedirectMain") + "editUsuario";
			response.sendRedirect(urlRedirect);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
