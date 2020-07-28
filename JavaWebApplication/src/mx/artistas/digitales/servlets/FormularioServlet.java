package mx.artistas.digitales.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.artistas.digitales.beans.FormularioBean;
import mx.artistas.digitales.beans.UsuarioBean;
import mx.artistas.digitales.daos.FormularioDAO;
import mx.artistas.digitales.daos.RegistroUsuarioDAO;


@WebServlet("/FormularioServlet")
public class FormularioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesaPeticion(request,response);
	}
	
	protected void procesaPeticion(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out	=	null;
		try {
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			System.out.println("Entro en el Servlet");
			String nomCompleto 	= request.getParameter("nomCompleto");
			String email 		= request.getParameter("email");
			String pass 		= request.getParameter("password");
			String sexo 		= request.getParameter("sexo");
			String pais 		= request.getParameter("pais");
			String idRol 		= request.getParameter("roles");
			String action		= request.getParameter("action");
			String idUserUpdate	= request.getParameter("idUsuario");
			
			RegistroUsuarioDAO registroDAO = new RegistroUsuarioDAO();
			
			HttpSession session = request.getSession();
			UsuarioBean userSession = (UsuarioBean)session.getAttribute("usuarioBean");
			boolean paso = false;
			if(action.equals("new")) {
				UsuarioBean usuarioBean = new UsuarioBean(nomCompleto,email,sexo,pais,Integer.parseInt(idRol),pass);
				paso = registroDAO.insertUser(usuarioBean);
			}if(action.equals("edit")) {
				UsuarioBean usuarioBean = new UsuarioBean(Integer.parseInt(idUserUpdate),nomCompleto,email,sexo,pais,Integer.parseInt(idRol),pass);
				paso = registroDAO.editUser(usuarioBean, userSession.getIdUsuario());
			}
			
			if(paso) {
				Cookie[] cookies = request.getCookies();
				String vecesInsertadas = null;
				if(cookies != null) { //Si hay cookies
					for(int i = 0; i < cookies.length; i++) {
						if(cookies[i].getName().equals("cookieInsert") ) {
							vecesInsertadas = cookies[i].getValue();
							break;
						}
					}
				}
				
				int count = 0;
				if(vecesInsertadas == null) {
					count = 1;
				}else {
					count = Integer.parseInt(vecesInsertadas) + 1;
				}
				Cookie c = new Cookie("cookieInsert", String.valueOf(count));
				//Añadir la cookie a las cabeceras de la respuesta HTTP
				response.addCookie(c);
				
				String redirectPage = (String)session.getAttribute("urlRedirectMain");
				response.sendRedirect(redirectPage + "abcUsuario");
				/*out.println("<html>");
				out.println("<h1> El usuario "+ nomCompleto +" Se insert correctamente</h1>");
				out.println("<h2> Has insertado a: " + count + (count==1?" persona.":" personas:") + "</h2>");
				out.println("</html>");*/
			}else {
				out.print("<h1> Ocurrio un error al insertar el usuario "+ nomCompleto);
			}
			
			
			/*System.out.println("nomCompleto: " + nomCompleto);
			System.out.println("email: " + email);
			System.out.println("pass: " + pass);
			System.out.println("sexo: " + sexo);
			System.out.println("pais: " + pais);*/
			/*out.println("nomCompleto: " + nomCompleto);
			out.println("email: " + email);
			out.println("pass: " + pass);
			out.println("sexo: " + sexo);
			out.println("pais: " + pais);
			*/
			//Obtener valor de un check box
			/*String [] values = request.getParameterValues("hobbie");
			for(String val : values) {
				out.println("Hobbies: " + val);
			}*/
			/* Para obtener todos los valores de un formulario
			Enumeration nombresParametros = request.getParameterNames();
			String parametro	=	"";
			String valor 		= "";
			while(nombresParametros.hasMoreElements()) {
				parametro = (String)nombresParametros.nextElement();
				valor = request.getParameter(parametro);
				out.println("<h3>valor " + valor + "</h3>");
			}*/
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.close();
		}
		
		
	}

}
