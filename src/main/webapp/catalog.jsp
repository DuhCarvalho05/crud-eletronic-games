<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="includes/header.jsp" />

<style>
.c-card {
	border: 1px solid black;
	width: 200px;
	height: 300px;
}
</style>

<div class="container">
	
	<c:if test="${ not empty category }">
		<h1>${ category.name }</h1>
	</c:if>
	
	<c:if test="${ not empty games }">
		<section class="d-flex flex-wrap gap-3">
			<c:forEach var="game" items="${ games }">
			<div class="c-card d-flex rounded position-relative">
				<a class="d-flex w-100" href="${pageContext.request.contextPath}/details/${ game.id }"> <img
					class="w-100 rounded"
					src="${pageContext.request.contextPath}/image/${game.imageName}"
					alt="Jogo ${ game.title }" />
				</a>
				<h5 class="position-absolute top-50 start-50 translate-middle">${ game.id }</h5>
			</div>
		</c:forEach>
		</section>
	</c:if>
</div>

<c:import url="includes/footer.jsp" />