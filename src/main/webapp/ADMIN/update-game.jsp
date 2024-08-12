<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../includes/header.jsp" />

<c:if test="${ user.type != 'ADMIN' }">
	<c:redirect url="/login.jsp"></c:redirect>
</c:if>

<div class="container d-flex justify-content-center mt-5">

	<div class="w-50 p-3">
		<span class="fw-bold fs-4 text-center">Atualizar Jogo</span>
		<form action="${pageContext.request.contextPath}/update-game"
			method="POST" enctype="multipart/form-data">
			<input type="hidden" name="id" value="${ game.id }" />
			<div class="mb-3">
				<label for="name" class="form-label">Titulo Jogo</label> <input
					style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;"
					type="text" class="form-control me-2 input" name="title"
					value="${ game.title }">
			</div>
			<div class="mb-3">
				<label for="image" class="form-label">Imagem do jogo</label> <input
					style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;"
					class="form-control input" type="file" name="image">
			</div>
			<div class="mb-3">
				<label for="publisher" class="form-label">Distribuidora/Desenvolvedora</label>
				<input
					style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;"
					type="text" class="form-control input" name="publisher"
					value="${ game.publisher }">
			</div>
			<div class="mb-3">
				<label for="release" class="form-label">Lançamento</label> <input
					style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;"
					type="text" class="form-control input" name="release"
					value="${ game.release }">
			</div>
			<div class="mb-3">
				<label for="synopsis" class="form-label">Sinopse</label> <input
					style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;"
					type="text" class="form-control input" name="synopsis"
					value="${ game.synopsis }">
			</div>
			<div class="mb-3">
				<label for="categoryId" class="form-label">Categoria</label> <input
					style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;"
					type="text" class="form-control input" name="categoryId"
					value="${ game.category.id }">
			</div>
			<div class="mb-3">
				<label for="requirement" class="form-label">Requerimentos do
					sistema</label>
				<textarea
					style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;"
					class="form-control input" name="requirement" rows="5" cols="20"
					placeholder="Componente;Valor (Enter para ir para proxima linha)"><c:forEach
						var="entry" items="${ game.requirement }">${entry.key};${entry.value}
</c:forEach></textarea>
			</div>
			<div class="mb-3">
				<label for="platform" class="form-label">Plataformas
					suportadas</label> <textarea
					style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;"
					class="form-control input" name="platform" rows="5" cols="20"
					placeholder="Nintendo;Xbox;PC"><c:forEach
						var="entry" items="${ game.platform }">${entry};</c:forEach></textarea>
			</div>
			
			<div class="d-flex gap-3 justify-content-end">
				<a class="btn btn-secondary btn-sm"
					href="${pageContext.request.contextPath}/game-list">Cancelar</a>
				<button type="submit" class="btn btn-light btn-sm">Atualizar
					Jogo</button>
			</div>
		</form>
	</div>

</div>


<c:import url="../includes/footer.jsp" />