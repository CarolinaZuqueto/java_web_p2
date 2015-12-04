<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="stylesheet" type="text/css"  href="ZuDoces/estiloMenu.css"/>-->
<title>ZuDoces - Sua loja de doces!</title>
</head>
<body>

		<c:if test="${empty usuario}">
			<p>Bem vindo, Usuário!</p>
		</c:if>
		<c:if test="${!empty usuario}">
			<p>Bem vindo, ${usuario}!</p>
		</c:if>
	  	<ul class="menu">
	        <li><a href="#">Cadastro</a>
	        	<ul>
	        		<li><a href="cadastroProduto.jsp">Produto</a></li>	        		
	        		<li><a href="cadastroUsuario.jsp">Usuário</a></li>
	        	</ul>	
	        </li>
	        <li><a href="logar.jsp">Logar</a></li>            
		</ul>

</body>
</html>