<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../includes/header.jsp" />

<c:if test="${ user.type != 'ADMIN' }">
	<c:redirect url="/login.jsp"></c:redirect>
</c:if>

<form action="${pageContext.request.contextPath}/update-game"

			method="POST" enctype="multipart/form-data">
			<div class="mb-3">
			<input type="hidden" class="form-control" name="id" value="${ game.id }">
			
				<label for="name" class="form-label">Titulo Jogo</label> <input
					type="text" class="form-control" name="title" value="${ game.title }">
			</div>
			<div class="mb-3">
			 <img class="w-15 rounded"
									src="${pageContext.request.contextPath}/image/${game.imageName}"
									alt="Jogo ${ game.title }" />
				<label for="image" class="form-label">Imagem do jogo</label> <input
					class="form-control" type="file" name="image">
			</div>
			<div class="mb-3">
				<label for="publisher" class="form-label">Distribuidora/Desenvolvedora</label>
				<input type="text" class="form-control" name="publisher" value="${ game.publisher }">
			</div>
			<div class="mb-3">
				<label for="release" class="form-label">Lançamento</label> <input
					type="text" class="form-control" name="release" value="${ game.release }">
			</div>
			<div class="mb-3">
				<label for="synopsis" class="form-label">Sinopse</label> <input
					type="text" class="form-control" name="synopsis" value="${ game.synopsis }">
			</div>
			<div class="mb-3">
				<label for="categoryId" class="form-label">Categoria</label> <input
					type="text" class="form-control" name="categoryId" value="${ game.category.id }">
			</div>
			<div class="mb-3">
				<label for="requirement" class="form-label">Requerimentos do
					sistema</label> <input type="text" class="form-control" name="requirement" value="${ game.requirement }">
			</div>
			<div class="mb-3">
				<label for="platform" class="form-label">Plataformas
					suportadas</label> <input type="text" class="form-control" name="platform" value="${ game.platform }">
			</div>
			<button type="submit" class="btn btn-primary">Atualizar
				Jogo</button>
</form>


<c:import url="../includes/footer.jsp" />