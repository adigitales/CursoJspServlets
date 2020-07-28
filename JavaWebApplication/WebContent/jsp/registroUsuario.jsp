<%@page import="mx.artistas.digitales.view.RegistroUsuarioView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mx.artistas.digitales.view.RegistroUsuarioView" %>
<%@page import="java.util.List" %>
<%@page import="mx.artistas.digitales.beans.RolesBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Formulario</title>
<link rel="stylesheet" href="../css/formulario.css" />
</head>
<%
	RegistroUsuarioView regView = new RegistroUsuarioView();
	List<RolesBean> listRoles = regView.getRoles();
%>
<body>

<div class="back">
	<a href="<%=session.getAttribute("urlRedirectMain") + "abcUsuario" %>">Regresar</a>
</div>
<div class="contenedor-registro-usuario">
	<form action="/JavaWebApplication/FormularioServlet" method="post">
		<div class="tit-reg-user">
			<span>Registro Usuario</span>
		</div>
		<main>
			<input type="text" name="nomCompleto" placeholder="Nombre Completo" />
			<input type="email" name="email" placeholder="E-mail" />
			<input type="password" name="password" placeholder="Contraseña" />
			<fieldset class="field-sexo">
				<legend>Sexo</legend>
				<div class="div-sexo">
					<input id="m" type="radio" name="sexo" value="M" checked="checked"/>
					<label for="m">Masculino</label>
					<input id="f" type="radio" name="sexo" value="F"/>
					<label for="f">Femenino</label>
				</div>
			</fieldset>
			
			<fieldset class="field-pais">
				<legend>País Origen</legend>
				<div class="div-pais">
					<select class="select-pais" name="pais" id="pais">
						<option class="class-pais" value="MX">México</option>
						<option class="class-pais" value="ES">España</option>
						<option class="class-pais" value="AR">Argentina</option>
						<option class="class-pais" value="EU">EU</option>
					</select>
				</div>
			</fieldset>
			 
			<fieldset class="field-roles">
			<legend>Roles</legend>
			<div class="div-roles">
				<select class="select-roles" name="roles" id="roles">
				<%for(RolesBean rol : listRoles){ %>
						<option class="class-pais" value="<%=rol.getIdRol() %>"><%=rol.getNomRol() %></option>
				<%} %>
					</select>
			</div>
			</fieldset>
			 <input type="hidden" name="action" value="new">
			<input type="submit"  value="Enviar"/>
		</main>
	
	</form>
</div>
</body>
</html>