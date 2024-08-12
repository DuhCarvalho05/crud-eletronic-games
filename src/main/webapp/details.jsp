<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="includes/header.jsp" />

<style>
.input::placeholder{
	color: #96969e;
}
</style>

<div class="container">
	<c:if test="${ not empty game }">
		<h3>${ game.title }</h3>
		
		<div class="d-flex gap-5 mb-5">
			<img style="width: 300px; height: 400px; border: 1px solid #27272a;" class="rounded" src="${pageContext.request.contextPath}/image/${game.imageName}"
			alt="Jogo ${ game.title }" />
			
			<div>
				<div class="row">
					<span class="fw-bold fs-6">Informações gerais</span>
					<div class="table-responsive">
						<table class="table table-bordered table-dark">
						  <thead>
						    <tr>
						      <th scope="col">Distribuidora</th>
						      <th scope="col">Lançamento</th>
						      <th scope="col">Sinopse</th>
						      <th scope="col">Categoria</th>
						     </tr>
						  </thead>
						  <tbody>
						  	<tr>
						      <td>${ game.publisher }</td>
						      <td>${ game.release }</td>
						      <td>${ game.synopsis }</td>
						      <td>${ game.category.name }</td>
						    </tr>
						  </tbody>
						</table>
					</div>
				</div>
				
				<div class="row">
					<div class="col-6">
						<span class="fw-bold fs-6">Requisitos do sistema</span>
						<div class="table-responsive">
							<table class="table table-bordered table-dark">
							  <thead>
							    <tr>
							      <th scope="col">Componente</th>
							      <th scope="col">Descrição</th>
							    </tr>
							  </thead>
							  <tbody>
								  <c:forEach var="entry" items="${game.requirement}">
									  <tr>
									      <td>${entry.key}</td>
									      <td>${entry.value}</td>
									    </tr>
									</c:forEach>
							  </tbody>
							</table>
						</div>
					</div>
			
					<div class="col-6">
						<span class="fw-bold fs-6">Plataformas suportadas</span>
						<ul class="list-group">
							<c:forEach var="platform" items="${game.platform}">
							  <li style="border-color: rgb(77, 81, 84); border-radius: 0;" class="list-group-item bg-dark text-light">${platform}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		
	</c:if>
	<c:if test="${ not empty user }">
		<div class="d-flex align-items-center gap-2">
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
			  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
			  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
			</svg>
			<span class="fw-bold fs-5">Deixe um comentário!</span>
		</div>
		<div style="border: 1px solid #27272a; padding: 10px" class="rounded mb-5">
			<form action="${pageContext.request.contextPath}/register-rating" method="POST">
				<input type="hidden" class="form-control" name="gameId" value="${ game.id }">
				<input type="hidden" class="form-control" name="userId" value="${ user.id }">
			
				<div class="mb-3">
					<label for="name" class="form-label">Nota</label>
			    	<input style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;"
						type="number" class="form-control input" name="stars" min="1" max="5" value="5" />
				</div>			
			  	<div class="mb-3">
			    	<label for="name" class="form-label">Descrição</label>
			    	<textarea style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;" 
			    	class="form-control" name="description" rows="4" cols="50">Otimo jogo!</textarea>
			  	</div>
			  	<div class="d-flex justify-content-end">
			  		<button type="submit" class="btn btn-light btn-sm">
				  		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
						  <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576zm6.787-8.201L1.591 6.602l4.339 2.76z"/>
						</svg>
			  			Enviar
			  		</button>
			  	</div>
			</form>
		</div>
	</c:if>
	
	<c:if test="${ not empty ratings }">
		<h3>Avaliações</h3>
		<div style="border: 1px solid #27272a; padding: 10px" class="rounded mb-3">
			<c:forEach var="rating" items="${ ratings }">
				<div class="d-flex align-items-center gap-2">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
					  <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
					</svg>
					<span> ${ rating.stars } </span>
				</div>
				<div class="d-flex justify-content-between">
					<p> ${ rating.user.email } </p>
					<span style="font-size: 12px"> ${ rating.createdAt }</span>
				</div>
				<p> ${ rating.description } </p>
			</c:forEach>
		</div>
	</c:if>
</div>

<c:import url="includes/footer.jsp" />