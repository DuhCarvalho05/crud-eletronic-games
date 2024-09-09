import { getAll, getUnique } from "../../api/services.js";
import layout from "../../layouts/layout.js";

const ratings = (props) => {
	const { description, stars, createdAt, user } = props;
	
	const createdAtDate = new Date(createdAt);
	const formattedDate = `${createdAtDate.getDate()}/${createdAtDate.getMonth()+1}/${createdAtDate.getFullYear()}`
	
	return div(
		div(
			span().innerHTML$(`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-star-fill" viewBox="0 0 16 16">
					  <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
					</svg>`),
			span(stars.toString())
		).class$("d-flex align-items-center gap-2"),
		div(
			p(user.email),
			span(formattedDate).style$("font-size: 12px")
		).class$("d-flex justify-content-between"),
		p(description)
	).class$("rounded mb-3")
	.style$("border: 1px solid #27272a; padding: 10px");
}

const game = (game) => {
	const { id, title, synopsis, publisher, release, imageName, category, requirement, platform } = game;
	
	const releaseDate = new Date(release);
	const formattedDate = `${releaseDate.getDate()}/${releaseDate.getMonth()+1}/${releaseDate.getFullYear()}`
	
	setTimeout(()=>{
		Object.keys(requirement).map( (key) => {
			document.getElementById("requirements").appendChild(
				tr(
					td(key),
					td(requirement[key])
				)
			)
		})
		
		platform.map( (plat) => document.getElementById("platforms").appendChild(
			li(plat).class$("list-group-item bg-dark text-light")
							.style$("border-color: rgb(77, 81, 84); border-radius: 0;")
		))
	}, 0)
	
	return div(
		img("image/" + imageName).att$("alt", "Capa do jogo "+title),
		div(
			div(
				span("Informações gerais").class$("fw-bold fs-6"),
				div(
					table(
						thead(
							tr(
								th("Distribuidora").att$("scope", "col"),
								th("Lançamento").att$("scope", "col"),
								th("Sinopse").att$("scope", "col"),
								th("Categoria").att$("scope", "col"),
							)
						),
						tbody(
							tr(
								td(publisher),
								td(formattedDate),
								td(synopsis),
								td(category.name),
							)
						)
					).class$("table table-bordered table-dark")
				).class$("table-responsive")
			).class$("row"),
			div(
				div(
					span("Requisitos do sistema").class$("fw-bold fs-6"),
					div(
						table(
							thead(
								tr(
									th("Componente").att$("scope", "col"),
									th("Descrição").att$("scope", "col"),
								)
							),
							tbody().id$("requirements")
						).class$("table table-bordered table-dark")
					).class$("table-responsive")
				).class$("col-6"),
				div(
					span("Plataformas suportadas").class$("fw-bold fs-6"),
					ul().id$("platforms")
					.class$("list-group")
				).class$("col-6")
			).class$("row")
		)
	).class$("d-flex gap-5 mb-5");
}


const details = (props) => {
  const gameId = props[0];
	
  getUnique(`/game/${gameId}`, "content", game);
  
  setTimeout(()=>{
	  getAll(`/ratings?gameId=${gameId}`, "ratings", ratings)
  }, 0)

  return layout(
		  	div().id$("content"),
	  		div(h3("Avaliações")).id$("ratings")
  		);
};

export default details;
