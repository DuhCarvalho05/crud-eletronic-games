import { onRegisterGame } from "../../../api/services.js"
import adminLayout from "../../../layouts/admin.js";

const registerGame = () => {

	const handleRegisterGame = async (e) => {
		e.preventDefault();

		const form = new FormData(document.getElementById("register-game"));

		await onRegisterGame(form);
	}

	const handlePressEnter = (e) => {
		if (e.keyCode === 13) {
			e.preventDefault();
			handleRegisterGame(e);
		}
	}

	return adminLayout(
		div(
			div(
				div(
					span("Novo Jogo").class$("fw-bold fs-4 text-center"),
					form(
						div(
							label("Titulo Jogo").class$("form-label"),
							input("text")
								.class$("form-control me-2 input")
								.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
								.att$("name", "title")
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
								.att$("name", "publisher"),
						).class$("mb-3"),
						div(
							label("Lançamento").class$("form-label"),
							input("text")
								.class$("form-control me-2 input")
								.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
								.att$("name", "release"),
						).class$("mb-3"),
						div(
							label("Sinopse").class$("form-label"),
							textarea()
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
								.att$("name", "categoryId"),
						).class$("mb-3"),
						div(
							label("Requerimentos do sistema").class$("form-label"),
							textarea()
								.class$("form-control me-2 input")
								.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
								.att$("name", "requirement")
								.att$("placeholder", "Componente;Valor (Shitf+Enter para ir para proxima linha)")
								.att$("rows", "5")
								.att$("cols", "20")
						).class$("mb-3"),
						div(
							label("Plataformas suportada").class$("form-label"),
							input("text")
								.class$("form-control me-2 input")
								.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
								.att$("name", "platform"),
						).class$("mb-3"),
						div(
							a("Cancelar")
								.class$("btn btn-secondary btn-sm")
								.att$("href", "#/games"),
							button("Cadastrar Jogo")
								.class$("btn btn-light btn-sm")
								.att$("type", "submit")
								.onclick$(handleRegisterGame)
						).class$("d-flex gap-3 justify-content-end")
					)
						.id$("register-game")
						.att$("method", "POST")
						.onkeydown$(handlePressEnter)
				).class$("w-50 p-3")
			).class$("container d-flex justify-content-center mt-5"),
		)
	)
}

export default registerGame;