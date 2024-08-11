<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="includes/header.jsp" />

<style>
.swiper {
	width: 100%;
}

.c-card {
	width: 200px;
	height: 300px;
}
</style>

<div class="container">
	<!-- Slider main container -->
	<div class="swiper">
		<h3>Plataforma</h3>
		<!-- Additional required wrapper -->
		<div class="swiper-wrapper">
			<!-- Slides -->
			<c:if test="${ not empty games }">
				<c:forEach var="game" items="${ games }">
					<c:if test="${ game.category.name == 'PLATAFORMA' }">
						<div class="swiper-slide">
							<div class="c-card d-flex rounded position-relative">
								<a class="d-flex w-100" href="details/${ game.id }"> <img class="w-100 rounded"
									src="${pageContext.request.contextPath}/image/${game.imageName}"
									alt="Jogo ${ game.title }" />
								</a>
								<h5 class="position-absolute top-50 start-50 translate-middle">${ game.id }</h5>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
		<!-- If we need pagination -->
		<div class="swiper-pagination"></div>

		<!-- If we need navigation buttons -->
		<div class="swiper-button-prev"></div>
		<div class="swiper-button-next"></div>
	</div>

	<!-- Slider main container -->
	<div class="swiper">
		<h3>RPG</h3>
		<!-- Additional required wrapper -->
		<div class="swiper-wrapper">
			<!-- Slides -->
			<c:if test="${ not empty games }">
				<c:forEach var="game" items="${ games }">
					<c:if test="${ game.category.name == 'RPG' }">
						<div class="swiper-slide">
							<div class="c-card rounded d-flex position-relative">
								<a class="d-flex w-100" href="${pageContext.request.contextPath}/details/${ game.id }"> <img class="w-100 rounded"
									src="${pageContext.request.contextPath}/image/${game.imageName}"
									alt="Jogo ${ game.title }" />
								</a>
								<h5 class="position-absolute top-50 start-50 translate-middle">${ game.id }</h5>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
		<!-- If we need pagination -->
		<div class="swiper-pagination"></div>

		<!-- If we need navigation buttons -->
		<div class="swiper-button-prev"></div>
		<div class="swiper-button-next"></div>
	</div>

	<!-- Slider main container -->
	<div class="swiper">
		<h3>FPS</h3>
		<!-- Additional required wrapper -->
		<div class="swiper-wrapper">
			<!-- Slides -->
			<c:if test="${ not empty games }">
				<c:forEach var="game" items="${ games }">
					<c:if test="${ game.category.name == 'FPS' }">
						<div class="swiper-slide">
							<div class="c-card rounded d-flex position-relative">
								<a class="d-flex w-100" href="${pageContext.request.contextPath}/details/${ game.id }"> <img class="w-100 rounded"
									src="${pageContext.request.contextPath}/image/${game.imageName}"
									alt="Jogo ${ game.title }" />
								</a>
								<h5 class="position-absolute top-50 start-50 translate-middle">${ game.id }</h5>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</c:if>
		</div>
		<!-- If we need pagination -->
		<div class="swiper-pagination"></div>

		<!-- If we need navigation buttons -->
		<div class="swiper-button-prev"></div>
		<div class="swiper-button-next"></div>
	</div>
</div>



<c:import url="includes/footer.jsp" />

<script type="module">
  import Swiper from 'https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.mjs'

  const swiper = new Swiper('.swiper', {
  slidesPerView: 6,
  spaceBetween: 10,
  // Optional parameters
  direction: 'horizontal',
  loop: false,

  // If we need pagination
  pagination: {
    el: '.swiper-pagination',
  },

  // Navigation arrows
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },

});
</script>