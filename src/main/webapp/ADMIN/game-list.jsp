<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../includes/header.jsp" />

<div class="container">

	<a class="btn btn-primary" href="${pageContext.request.contextPath}/register-game">Cadastrar novo jogo</a>

	<div class="table-responsive">
		<table class="table table-bordered table-dark table-hover">
			<thead>
				<tr>
					<td>Id</td>
					<td>Titulo</td>
					<td>Ações</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${ empty games }">
					<tr>
						<td class="text-center" colspan="4">Não há nenhum jogo cadastrado.</td>
					</tr>
				</c:if>

				<c:if test="${ not empty games }">
					<c:forEach var="game" items="${ games }">
						<tr>
							<td class="align-middle">${game.id}</td>
							<td class="align-middle">${game.title}</td>
							<td>
								<div class="d-flex align-items-center gap-2">
									<a href="${pageContext.request.contextPath}/update-game/${game.id}"> <svg xmlns="http://www.w3.org/2000/svg"
											width="16" height="16" fill="currentColor"
											class="bi bi-pencil-square" viewBox="0 0 16 16">
										  <path
												d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
										  <path fill-rule="evenodd"
												d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z" />
										</svg>
									</a> <a href="${pageContext.request.contextPath}/delete-game/${game.id}"> <svg xmlns="http://www.w3.org/2000/svg"
											width="16" height="16" fill="#ff0000" class="bi bi-trash"
											viewBox="0 0 16 16">
										  <path
												d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z" />
										  <path
												d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z" />
										</svg>
									</a>
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

	</div>
</div>

<c:import url="../includes/footer.jsp" />