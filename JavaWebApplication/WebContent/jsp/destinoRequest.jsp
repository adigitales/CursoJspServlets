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
		Integer vecesIngresado = (Integer)request.getAttribute("cuenta_ingreso");
		
		
	%>
	<h1>Lo tengo! el valor de la variable es: <%=vecesIngresado%> </h1>
</body>
</html>