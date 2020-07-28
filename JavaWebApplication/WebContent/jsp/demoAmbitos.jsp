<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String saludo = "Hola Mundo";
		Integer vecesIngresado = (Integer)session.getAttribute("cuenta_ingreso");
		String s = " vez.";
		if(vecesIngresado == null){
			vecesIngresado = 1;
		}else{
			vecesIngresado = vecesIngresado + 1;
			s = " veces.";
		}
		session.setAttribute("cuenta_ingreso", vecesIngresado);
		//request.getRequestDispatcher("destinoRequest.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/jsp/destinoRequest.jsp");
	%>
	<h1>Has visitado esta página: <%=vecesIngresado%> <%=s%> </h1>
	<h2><%=saludo%></h2>
</body>
</html>