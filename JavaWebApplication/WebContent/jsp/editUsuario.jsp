<%@page import="mx.artistas.digitales.view.RegistroUsuarioView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mx.artistas.digitales.view.RegistroUsuarioView" %>
<%@page import="java.util.List" %>
<%@page import="mx.artistas.digitales.beans.RolesBean" %>
<%@page import="mx.artistas.digitales.beans.UsuarioBean" %>
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
	UsuarioBean usuarioBean = (UsuarioBean)session.getAttribute("editUsuarioBean");
%>
<body>

<div class="back">
	<a href="<%=session.getAttribute("urlRedirectMain") + "abcUsuario" %>">Regresar</a>
</div>
<div class="contenedor-registro-usuario">
	<form action="/JavaWebApplication/FormularioServlet" method="post">
		<div class="tit-reg-user">
			<span>Edición de Usuario</span>
		</div>
		<main>
			<input type="text" name="nomCompleto" placeholder="Nombre Completo" value="<%=usuarioBean.getNomCompleto() %>" />
			<input type="email" name="email" placeholder="E-mail" value="<%=usuarioBean.getCorreo() %>" />
			<input type="password" name="password" placeholder="Contraseña" />
			<fieldset class="field-sexo">
				<legend>Sexo</legend>
				<div class="div-sexo">
					<input id="m" type="radio" name="sexo" value="M" <%=usuarioBean.getSexo().equals("M")?"checked='checked'":"" %> />
					<label for="m">Masculino</label>
					<input id="f" type="radio" name="sexo" value="F" <%=usuarioBean.getSexo().equals("F")?"checked='checked'":"" %>/>
					<label for="f">Femenino</label>
				</div>
			</fieldset>
			
			<fieldset class="field-pais">
				<legend>País Origen</legend>
				<div class="div-pais">
					<select class="select-pais" name="pais" id="pais">
						<option class="class-pais" value="MX" <%=usuarioBean.getPais().equals("MX")?"selected='selected'":"" %> >México</option>
						<option class="class-pais" value="ES" <%=usuarioBean.getPais().equals("ES")?"selected='selected'":"" %>>España</option>
						<option class="class-pais" value="AR" <%=usuarioBean.getPais().equals("AR")?"selected='selected'":"" %>>Argentina</option>
						<option class="class-pais" value="EU" <%=usuarioBean.getPais().equals("EU")?"selected='selected'":"" %>>EU</option>
					</select>
				</div>
			</fieldset>
			 
			<fieldset class="field-roles">
			<legend>Roles</legend>
			<div class="div-roles">
				<select class="select-roles" name="roles" id="roles">
				<%for(RolesBean rol : listRoles){ %>
						<option class="class-pais" value="<%=rol.getIdRol() %>" <%=usuarioBean.getIdRol() == rol.getIdRol()?"selected='selected'":"" %> ><%=rol.getNomRol() %></option>
				<%} %>
					</select>
			</div>
			</fieldset>
			 <input type="hidden" name="action" value="edit">
			 <input type="hidden" name="idUsuario" value="<%=usuarioBean.getIdUsuario()%>">
			<input type="submit"  value="Modificar"/>
		</main>
	
	</form>
</div>
</body>
</html>