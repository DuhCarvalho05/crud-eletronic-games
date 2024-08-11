<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<title>GAMES</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,-25" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
</head>

<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/home">GAMES</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Categorias </a>
						<ul class="dropdown-menu">
							<c:if test="${ not empty categories }">
								<c:forEach var="category" items="${ categories }">
									<li><a class="dropdown-item"
										href="${pageContext.request.contextPath}/catalog/${ category.name }">${ category.name }</a></li>
								</c:forEach>
							</c:if>
						</ul></li>
					<c:if test="${ not empty user }">
						<c:if test="${ user.type == 'ADMIN' }">
							<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">
								Configurações </a>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath}/category-list">Lista
										de categorias cadastradas</a></li>
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath}/game-list">Lista
										de jogos cadastrados</a></li>
							</ul></li>
						</c:if>
					</c:if>
				</ul>
			</div>

			<form class="d-flex" role="search"
				action="${pageContext.request.contextPath}/search" method="GET">
				<input class="form-control me-2" name="q" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>

			<c:choose>
				<c:when test="${ not empty user }">
					<div class="dropdown">
						<button class="btn btn-secondary dropdown-toggle" type="button"
							data-bs-toggle="dropdown" aria-expanded="false">
							<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30"
								fill="currentColor" class="bi bi-person-circle"
								viewBox="0 0 16 16">
				  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0" />
				  <path fill-rule="evenodd"
									d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1" />
				</svg>
						</button>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item">${ user.name }</a></li>
							<li><a class="dropdown-item"
								href="${pageContext.request.contextPath}/logout">Sair</a></li>
						</ul>
					</div>
				</c:when>

				<c:otherwise>
					<a href="${pageContext.request.contextPath}/login"> <svg
							xmlns="http://www.w3.org/2000/svg" width="30" height="30"
							fill="currentColor" class="bi bi-person-circle"
							viewBox="0 0 16 16">
				  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0" />
				  <path fill-rule="evenodd"
								d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1" />
				</svg>
					</a>
				</c:otherwise>
			</c:choose>

		</div>
	</nav>