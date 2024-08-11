<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../includes/header.jsp" />

<div class="container d-flex justify-content-center mt-5">

	<div class="w-50 p-3 bg-light">
		<form action="${pageContext.request.contextPath}/update-category"
			method="POST">
			<input type="hidden" class="form-control" name="id" value="${ category.id }">
			<div class="mb-3">
				<label for="name" class="form-label">Nome categoria</label> <input
					type="text" class="form-control" name="name" value="${ category.name }">
			</div>
			<button type="submit" class="btn btn-primary">Atualizar
				categoria</button>
		</form>
	</div>

</div>
<c:import url="../includes/footer.jsp" />