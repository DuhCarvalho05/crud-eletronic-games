<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="../includes/header.jsp" />

<c:if test="${ user.type != 'ADMIN' }">
	<c:redirect url="/login.jsp"></c:redirect>
</c:if>

<div class="container d-flex justify-content-center mt-5"> 

	<div class="w-50 p-3 bg-light">
		<form action="${pageContext.request.contextPath}/register-category" method="POST">
		  	<div class="mb-3">
		    	<label for="name" class="form-label">Nome categoria</label>
		    	<input type="text" class="form-control" name="name">
		  	</div>
		  	<button type="submit" class="btn btn-primary">Cadastrar categoria</button>
		</form>
	</div>
	
</div>

<c:import url="../includes/footer.jsp" />