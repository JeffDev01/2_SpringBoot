<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:url value="/entrada?acao=AlteraEmpresa" var="linkServletNovaEmpresa" />

<% System.out.println("DENTRO DO JSP AINDA"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="${linkServletNovaEmpresa}" method="post">
	Alterar Dados da Empresa 	<c:import url="logout-parcial.jsp" />
	<br />
	<br />
		Nome: <input type="text" name="nome" value="${empresa.nome}"/> 
		Data Abertura: <input type="text" name="data" value="<fmt:formatDate value="${empresa.dataAbertura}" pattern ="dd/MM/yyyy"/>" /> 
		Id: <input type="hidden" name="id" value="${empresa.id}"/>
		<input type="submit" />
		
	</form>
</body>
</html>	