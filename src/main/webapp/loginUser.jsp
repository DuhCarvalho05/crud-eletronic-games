<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="includes/Header.jsp"></c:import>

<div class="container d-flex justify-content-center mt-5"> 
	
	<div class="w-50 p-3 bg-light">
		<form action="login" method="post">
		  	<div class="mb-3">
		    	<label for="exampleInputEmail1" class="form-label">Email address</label>
		    	<input type="email" class="form-control" name="email" aria-describedby="emailHelp">
		  	</div>
		  	<div class="mb-3">
		    	<label for="exampleInputPassword1" class="form-label">Password</label>
		    	<input type="password" class="form-control" name="password">
		  	</div>
		  	<div class="mb-3">
		   		<a href="cadastrar.jsp">Cadastre-se</a>
		  	</div>	
		  	<button type="submit" class="btn btn-primary">Enviar</button>
		</form>
	</div>
</div>

<c:import url="includes/Footer.jsp"></c:import>