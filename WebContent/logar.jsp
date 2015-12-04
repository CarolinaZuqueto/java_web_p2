<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Loggin</title>
</head>
<body>
	<p><a href="index.jsp">Home</a></p>
	<form action="LoginServlet" method="post">
		<p>Usuário: <input type="text" name="Usuario"></p>
		<p>Senha: <input type="password" name="Senha"></p>
		<input type="submit" value="Logar" name="button">
	</form>
	
	<p>${mensagem}</p>

</body>
</html>