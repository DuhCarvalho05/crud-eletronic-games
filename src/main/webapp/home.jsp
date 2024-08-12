<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="includes/header.jsp" />

<style>
.swiper {
	width: auto;
	padding-bottom: 20px
}
.swiper-pagination-bullets{
	bottom: -5px!important
}
.swiper .swiper-pagination-bullet{
	background: #fff!important;	
}
.c-card {
	width: 200px;
	height: 300px;
}
.c-card:hover{
	opacity: 0.7;
}
.category-link:hover{
	background-color: #fff;
	border-radius: 5px;
}
.category-link:hover a{
	color: #333!important;
}
</style>

<div class="container">

	<div class="row justify-content-between">
		<div style="border: 1px solid #27272a; height: fit-content; position: sticky; top: 70px" class="col-2 rounded p-3">
			<c:choose>
				<c:when test="${ empty categories }">
					<p>Nenhuma categoria cadastrada :(</p>	
				</c:when>
				
				<c:otherwise>
					<div class="d-flex align-items-center gap-2 mb-3">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bookmarks" viewBox="0 0 16 16">
						  <path d="M2 4a2 2 0 0 1 2-2h6a2 2 0 0 1 2 2v11.5a.5.5 0 0 1-.777.416L7 13.101l-4.223 2.815A.5.5 0 0 1 2 15.5zm2-1a1 1 0 0 0-1 1v10.566l3.723-2.482a.5.5 0 0 1 .554 0L11 14.566V4a1 1 0 0 0-1-1z"/>
						  <path d="M4.268 1H12a1 1 0 0 1 1 1v11.768l.223.148A.5.5 0 0 0 14 13.5V2a2 2 0 0 0-2-2H6a2 2 0 0 0-1.732 1"/>
						</svg>
						<span class="fw-bold">Categorias</span>
					</div>
					<ul style="list-style: none; padding: 0; margin: 0">
						<c:forEach var="category" items="${ categories }">
							<li style="margin-bottom: 10px;" class="category-link d-flex">
								<a style="text-decoration: none; color: #fff; padding: 0px 4px; width: 100%" class="text-capitalize fw-medium" href="${pageContext.request.contextPath}/catalog/${ category.name }">
									${ category.name } 
								</a>
							</li>
						</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	
		<div class="col-9">
			<c:choose>
				<c:when test="${ empty categories }">
					<p>Nenhuma categoria cadastrada :(</p>	
				</c:when>
				
				<c:otherwise>
					<c:forEach var="category" items="${ categories }">
						<div style="margin-bottom: 40px" class="swiper">
							<span class="fw-bold text-capitalize">${ category.name }</span>
							<div class="swiper-wrapper">
								<!-- Slides -->
								<c:if test="${ not empty games }">
									<c:forEach var="game" items="${ games }">
										<c:if test="${ game.category.id == category.id }">
											<div class="swiper-slide">
												<div style="border: 1px solid #27272a;" class="c-card d-flex rounded position-relative">
													<a class="d-flex w-100 p-2" href="details/${ game.id }"> 
													<img
														style="opacity: 0.8;"
														class="w-100 rounded"
														src="${pageContext.request.contextPath}/image/${game.imageName}"
														alt="Jogo ${ game.title }" />
													</a>
												</div>
												<span style="font-size: 12px; color: #96969e">${ game.title }</span>
											</div>
										</c:if>
									</c:forEach>
								</c:if>
							</div>
							<!-- If we need pagination -->
							<div class="swiper-pagination"></div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
</div>



<c:import url="includes/footer.jsp" />

<script type="module">
  import Swiper from 'https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.mjs'

  const swiper = new Swiper('.swiper', {
  slidesPerView: 4,
  spaceBetween: 0,
  // Optional parameters
  direction: 'horizontal',
  loop: false,

  // If we need pagination
  pagination: {
    el: '.swiper-pagination',
  },

});
</script>