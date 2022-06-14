<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="java.util.List,br.com.alura.gerenciador.modelo.Empresa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Java Standart Taglib</title>
</head>
<body>

	<br />
	<br />
	Usuario logado: ${usuarioLogado.login}  <c:import url="logout-parcial.jsp" />
	<ul>
	<br />
	Lista de Empresas Cadastradas com Data
	<br />
	<br />
		<c:forEach items="${listaEmpresas}" var="empresa">
			
				<li>${empresa.nome}  - <fmt:formatDate value="${empresa.dataAbertura}" pattern ="dd/MM/yyyy"/>
				<a href="/gerenciador/entrada?acao=MostraEmpresa&id=${empresa.id}">editar</a>
				<a href="/gerenciador/entrada?acao=DeletarEmpresa&id=${empresa.id}">remove</a>

		
				 
			
		</c:forEach>

	</ul>


</body>
</html>