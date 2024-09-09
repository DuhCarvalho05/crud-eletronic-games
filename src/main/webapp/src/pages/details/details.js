import { getAll, getUnique, onSendRating } from "../../api/services.js";
import { getUserSession } from "../../auth/session.js";
import layout from "../../layouts/layout.js";

const formRating = (props) => {

	const gameId = props;

	const userSession = getUserSession();

	const handleRating = async (e) => {
		e.preventDefault();
		const form = new FormData(document.getElementById("form-rating"));
		
		const userId = form.get("userId");
		const gameId = form.get("gameId");
		const stars = form.get("stars");
		const description = form.get("description");
		
		await onSendRating(gameId, JSON.stringify({userId, gameId, stars, description}));
	}

	if (userSession === null) return div(
		span("Acesse sua conta para deixar uma avaliação,"),
		a("acessar.").att$("href", "#/login")
	)

	return div(
		div(
			span().innerHTML$(`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
			  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
			  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z"/>
			</svg>`),
			span("Deixe um comentário!").class$("fw-bold fs-5")
		).class$("d-flex align-items-center gap-2"),
		div(
			form(
				input("hidden").att$("name", "userId").att$("value", userSession.id),
				input("hidden").att$("name", "gameId").att$("value", gameId),
				div(
					label("Nota")
						.class$("form-label"),
					input("number")
						.class$("form-control input")
						.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
						.att$("type", "number")
						.att$("name", "stars")
						.att$("min", "1")
						.att$("max", "5")
						.att$("value", "5")
				),
				div(
					label("Descrição")
						.class$("form-label"),
					textarea("Ótimo jogo!")
						.class$("form-control input")
						.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
						.att$("name", "description")
						.att$("rows", "5")
						.att$("cols", "20")
				).class$("mb-3"),
				div(
					button(
						span().innerHTML$(`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-send" viewBox="0 0 16 16">
						  <path d="M15.854.146a.5.5 0 0 1 .11.54l-5.819 14.547a.75.75 0 0 1-1.329.124l-3.178-4.995L.643 7.184a.75.75 0 0 1 .124-1.33L15.314.037a.5.5 0 0 1 .54.11ZM6.636 10.07l2.761 4.338L14.13 2.576zm6.787-8.201L1.591 6.602l4.339 2.76z"/>
						</svg>`),
						span("Enviar")
					)
						.class$("btn btn-light btn-sm")
						.att$("type", "submit")
						.onclick$(handleRating)
				).class$("d-flex justify-content-end")
			)
				.id$("form-rating")
				.att$("method", "POST")
		).class$("rounded mb-5")
			.style$("border: 1px solid #27272a; padding: 10px")
	);
}

const ratings = (props) => {
	const { description, stars, createdAt, user } = props;

	const createdAtDate = new Date(createdAt);
	const formattedDate = `${createdAtDate.getDate()}/${createdAtDate.getMonth() + 1}/${createdAtDate.getFullYear()}`

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
	const formattedDate = `${releaseDate.getDate()}/${releaseDate.getMonth() + 1}/${releaseDate.getFullYear()}`

	setTimeout(() => {
		Object.keys(requirement).map((key) => {
			document.getElementById("requirements").appendChild(
				tr(
					td(key),
					td(requirement[key])
				)
			)
		})

		platform.map((plat) => document.getElementById("platforms").appendChild(
			li(plat).class$("list-group-item bg-dark text-light")
				.style$("border-color: rgb(77, 81, 84); border-radius: 0;")
		))
	}, 0)

	return div(
		img("image/" + imageName).att$("alt", "Capa do jogo " + title).style$("width: 200px"),
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

	setTimeout(() => {
		getAll(`/ratings?gameId=${gameId}`, "ratings", ratings)
	}, 0)

	return layout(
		div().id$("content"),
		div(h3("Avaliações"), formRating(gameId.toString())).id$("ratings")
	);
};

export default details;
