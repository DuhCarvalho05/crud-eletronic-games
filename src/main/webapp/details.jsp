<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="includes/header.jsp" />

<div class="container">
	<c:if test="${ not empty game }">
		<h3>Jogo</h3>
		<p>${ game.id }</p>
		<p>${ game.title }</p>
		
		<img src="${pageContext.request.contextPath}/image/${game.imageName}"
			alt="Jogo ${ game.title }" />
			
		<p>${ game.publisher }</p>
		<p>${ game.release }</p>
		<p>${ game.synopsis }</p>
		<p>${ game.category.id }</p>
		<p>${ game.category.name }</p>

		<c:forEach var="entry" items="${game.requirement}">
		  Componente: <c:out value="${entry.key}" />
		  Descrição: <c:out value="${entry.value}" />
		</c:forEach>
		
		<c:forEach var="platform" items="${game.platform}">
		  Plataforma: <c:out value="${platform}" />
		</c:forEach>
	</c:if>
	
	<c:if test="${ not empty ratings }">
		<h3>Avaliações</h3>
		<c:forEach var="rating" items="${ ratings }">
			<p> ${ rating.id } </p>
			<p> ${ rating.description } </p>
			<p> ${ rating.stars } </p>
			<p> ${ rating.user.email } </p>
		</c:forEach>
	</c:if>
</div>

<c:import url="includes/footer.jsp" />