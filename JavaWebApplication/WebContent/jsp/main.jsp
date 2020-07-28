<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="mx.artistas.digitales.beans.UsuarioBean" %>

<%UsuarioBean usuarioBean = (UsuarioBean)session.getAttribute("usuarioBean"); 
 String urlLogut = request.getContextPath() + "/LogoutServlet";
 String urlPage  = request.getContextPath() + "/MenuRedirectServlet?pagina=";
 
%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<!--<script src="https://kit.fontawesome.com/cd24763895.js" crossorigin="anonymous"></script>-->
	<link rel="stylesheet" type="text/css" href="../css/layout1.css">
	<link rel="stylesheet" type="text/css" href="../icons/css/all.min.css">
	<script src="../jquery/jquery-3.5.1.min.js"></script>
	<script src="../js/layout1.js"></script>
	<title>Layout 1</title>
	<div class="principal-content">
		<header>
		<div>
			<img src="../img/ADigitales.png"/>
		</div>
			<div class="menu-bar">
				<a href="#"><span>Menú</span><i class="fas fa-bars"></i></a>
			</div>
			<ul class="menu">
				<li class="sub-menu">
					<a href="#"><i class="fas fa-caret-down"></i>Recursos Humanos</a>
					<ul class="children">
						<li><a href="<%=urlPage + "abcUsuario"%>">Usuario</a></li>
						<li><a href="<%=urlPage + "ambitos"%>"">Bajas</a></li>
						<li><a href="#">Modificaciones</a></li>
					</ul>
				</li>
				<li><a href="#"><i class="fas fa-folder"></i>Administración</a></li>
				<li><a href="#"><i class="fas fa-chart-bar"></i>Reportes</a></li>
				<li><a href="#"><i class="fas fa-cogs"></i>Configuración</a></li>
			</ul>
			
			<div class="usuario">
				<span><%=usuarioBean.getNomCompleto()%> </span>
				<span><%=usuarioBean.getNomRol() %></span>
				<span><a href="<%=urlLogut%>">Salir<i class="fas fa-sign-out-alt salir"></i></a></span>
			</div>
		</header>
		<main>
			
			 <jsp:include page="${sessionScope.page}"/> 
		</main>
		<footer>
			<img src="../img/ADigitales.png"/>
			<span>Todos los derechos reservados</span>
		</footer>

	</div>
</head>
<body>

</body>
</html>