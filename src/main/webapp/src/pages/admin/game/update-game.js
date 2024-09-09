import { getUnique, onUpdateGame } from "../../../api/services.js"
import adminLayout from "../../../layouts/admin.js";

const formGame = (props) => {

	const { id, title, synopsis, publisher, release, imageName, category, requirement, platform } = props

	let requirementString = "";

	Object.keys(requirement).map((key) => {
		requirementString += `${key};${requirement[key]}\n`;
	})
	
	let platformString = "";
	
	platform.map((plat) => platformString += `${plat};`)
	

	const handleUpdateGame = async (e) => {
		e.preventDefault();
		const form = new FormData(document.getElementById("update-game"));
		await onUpdateGame(id, form);
	}

	return form(
		input("hidden").att$("name", "id").att$("value", id),
		div(
			label("Titulo Jogo").class$("form-label"),
			input("text")
				.class$("form-control me-2 input")
				.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
				.att$("name", "title")
				.att$("value", title)
		),
		div(
			label("Imagem do jogo").class$("form-label"),
			input("file")
				.class$("form-control me-2 input")
				.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
				.att$("name", "image"),
		).class$("mb-3"),
		div(
			label("Distribuidora/Desenvolvedora").class$("form-label"),
			input("text")
				.class$("form-control me-2 input")
				.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
				.att$("name", "publisher")
				.att$("value", publisher),
		).class$("mb-3"),
		div(
			label("Lançamento").class$("form-label"),
			input("text")
				.class$("form-control me-2 input")
				.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
				.att$("name", "release")
				.att$("value", release),
		).class$("mb-3"),
		div(
			label("Sinopse").class$("form-label"),
			textarea(synopsis)
				.class$("form-control me-2 input")
				.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
				.att$("name", "synopsis")
				.att$("rows", "5")
				.att$("cols", "20")
		).class$("mb-3"),
		div(
			label("Categoria").class$("form-label"),
			input("number")
				.class$("form-control me-2 input")
				.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
				.att$("name", "categoryId")
				.att$("value", category.id),
		).class$("mb-3"),
		div(
			label("Requerimentos do sistema").class$("form-label"),
			textarea(requirementString)
				.class$("form-control me-2 input")
				.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
				.att$("name", "requirement")
				.att$("rows", "5")
				.att$("cols", "20")
		).class$("mb-3"),
		div(
			label("Plataformas suportada").class$("form-label"),
			input("text")
				.class$("form-control me-2 input")
				.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
				.att$("name", "platform")
				.att$("value", platformString),
		).class$("mb-3"),
		div(
			a("Cancelar")
				.class$("btn btn-secondary btn-sm")
				.att$("href", "#/games"),
			button("Atualizar Jogo")
				.class$("btn btn-light btn-sm")
				.att$("type", "submit")
				.onclick$(handleUpdateGame)
		).class$("d-flex gap-3 justify-content-end")
	)
		.id$("update-game")
		.att$("method", "POST")

}

const updateGame = (props) => {

	const gameId = props[0];

	getUnique(`/game/${gameId}`, "content", formGame);

	return adminLayout(
		div(
			div(
				div(
					span("Editar Jogo").class$("fw-bold fs-4 text-center"),
				).id$("content")
					.class$("w-50 p-3")
			).class$("container d-flex justify-content-center mt-5"),
		)
	)
}

export default updateGame;