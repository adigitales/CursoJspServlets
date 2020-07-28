<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="mx.artistas.digitales.view.AbcUsuarioView" %>
<%@page import="java.util.List" %>
<%@page import="mx.artistas.digitales.beans.UsuarioBean" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../icons/css/all.min.css">
<link rel="stylesheet" href="../css/abcUsuario.css" />	
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
function elimina(url){
	Swal.fire({ title: 'Confirmar', 
		text: '¿Estás seguro que desea borrar este registro?',
		icon: 'warning', 
		showCancelButton: true, 
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33', 
		confirmButtonText: 'Si'}).then((result) => {
			  if (result.value) {
					window.location.href = url; 
					return false
				  }
				})
}
function buscar(url){
	
	var textoBuscar = document.getElementById('txtBuscar').value;
	window.location.href = url + textoBuscar; 
	
}

function pulsar(e,url){
	 if (e.keyCode == 13) {
		 buscar(url);
	 }
}
</script>
</head>
<% 
	String nomBuscar = (String)session.getAttribute("nomBuscar");
	AbcUsuarioView abcView = new AbcUsuarioView();
	List<UsuarioBean> listUser = new ArrayList<>();
	if(nomBuscar != null){
		listUser = abcView.getUserFilter(nomBuscar);
		session.removeAttribute("nomBuscar");
	}else{
		listUser = abcView.getAllUSer();
	}
		
%>
<body>

	<div class="content-abcUsuario">
		
		<div class="div-busqueda">
			<div class="txt-busqueda">
				<input type="text" id="txtBuscar" name="txtBuscar" onkeypress="pulsar(event,'<%=request.getContextPath()+ "/BuscaUsuarioServlet?nomBuscar="%>')" placeholder="Buscar Por Nombre" value="<%=nomBuscar==null?"":nomBuscar%>" /> 
				<input type="submit" onclick="javascript:buscar('<%=request.getContextPath()+ "/BuscaUsuarioServlet?nomBuscar="%>')" value="Buscar">
			</div>
			<div class="new-user">
			 <a href="<%=session.getAttribute("urlRedirectMain") + "registroUsuario" %>"> <i class="fas fa-plus-circle"></i> <span>Nuevo</span>  </a>
			</div>
		</div>
		
		<div class="list-user">
			<table>
				<tr>
					<th>Nombre Completo</th>
					<th>Correo</th>
					<th>Sexo</th>
					<th>país</th>
					<th>Rol</th>
					<th>Editar</th>
					<th>Eliminar</th>
				</tr>
		<%for(UsuarioBean usuario : listUser){ %>		
				<tr>
					<td><%=usuario.getNomCompleto() %></td>
					<td><%=usuario.getCorreo() %></td>
					<td><%=usuario.getSexo() %></td>
					<td><%=usuario.getPais() %></td>
					<td><%=usuario.getNomRol() %></td>
					<td><a href="<%=request.getContextPath() + "/EditRegistoUsuarioServlet?nomCompleto="+usuario.getNomCompleto() + "&correo="+usuario.getCorreo() + "&sexo=" +usuario.getSexo() +
					"&pais=" + usuario.getPais() + "&idRol=" + usuario.getIdRol() + "&idUsuario=" + usuario.getIdUsuario() %>"> <i class="far fa-edit icon-edit"></i> </a></td>
					<td><i class="fas fa-minus-circle icon-delete" onclick="javascript:elimina('<%=request.getContextPath() + "/EliminaUsuarioServlet?idUsuario="+usuario.getIdUsuario() %>')"></i></td>
				</tr>
		<%} %>
			</table>
		</div>
	</div>
</body>
</html>