<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Usuário</title>
</head>
<body>
	<p><a href="index.jsp">Home</a></p>
	<form action="UsuarioServlet" method="post">
		<p>Login: <input type="text" name="Login"></p>
		<p>Email: <input type="text" name="Email"></p>
		<p>Senha: <input type="password" name="Senha"></p>
		<input type="submit" value="Cadastrar" name="button">
		<input type="submit" value="Alterar" name="button">
	</form>
	<p></p>
	<p>${mensagem}</p>
</body>
</html>