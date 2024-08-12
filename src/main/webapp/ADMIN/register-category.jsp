<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../includes/header.jsp" />

<c:if test="${ user.type != 'ADMIN' }">
	<c:redirect url="/login.jsp"></c:redirect>
</c:if>

<style>
.input::placeholder{
	color: #96969e;
}
</style>

<div class="container d-flex justify-content-center mt-5"> 

	<div class="w-50 p-3">
		<span class="fw-bold fs-4 text-center">Nova categoria</span>
		<form action="${pageContext.request.contextPath}/register-category" method="POST">
		  	<div class="mb-3">
		    	<label for="name" class="form-label">Nome categoria</label>
		    	<input
					style="background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;"
					type="text" class="form-control me-2 input" name="name" placeholder="Nome categoria">
		  	</div>
		  	<div class="d-flex gap-3 justify-content-end">
		  		<a class="btn btn-secondary btn-sm" href="${pageContext.request.contextPath}/category-list">Cancelar</a>
		  		<button type="submit" class="btn btn-light btn-sm">Cadastrar categoria</button>
		  	</div>
		</form>
	</div>
	
</div>

<c:import url="../includes/footer.jsp" />