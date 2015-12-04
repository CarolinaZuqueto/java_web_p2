<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Produto</title>
</head>
<body>
	<p><a href="index.jsp">Home</a></p>
	<form action="ProdutoServlet" method="post">
		<p>Código: <input type="text" name="CodigoProduto" value="${produto.codigo}"></p>
		<p>Nome: <input type="text" name="NomeProduto"  value="${produto.nome}"></p>
		<p>Descrição: <input type="text" name="DescricaoProduto" value="${produto.descricao}"></p>
		<p>Quantidade: <input type="text" name="QtdProduto"  value="${produto.quantidade}"></p>
		<p>Valor Unitário: <input type="text" name="ValorProduto"  value="${produto.valor}"></p>
		<input type="submit" value="Inserir" name="button">
		<input type="submit" value="Pesquisar" name="button">
		
		<c:if test="${pesquisarMode}">
			<input type="submit" value="Atualizar" name="button">
			<input type="submit" value="Excluir" name="button">
		</c:if>
		<c:if test="${!pesquisarMode}">
			<input type="submit" value="Atualizar" name="button" disabled="disabled">
			<input type="submit" value="Excluir" name="button" disabled="disabled">
		</c:if>
		
	</form>
	<p></p>
	<p>${mensagem}</p>
</body>
</html>