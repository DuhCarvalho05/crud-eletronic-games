import { refresh } from "../../../../router.js";
import { getAll, onDeleteGame } from "../../../api/services.js"
import adminLayout from "../../../layouts/admin.js";

const row = (props) => {
	const { id, title } = props;
	
	const handleDeleteGame = async () => {
		await onDeleteGame(id)
		refresh();
	}

	return tr(
		td(id.toString()),
		td(title),
		td(
			div(
				a(
					span()
						.innerHTML$(`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-pencil-square"
									viewBox="0 0 16 16">
								  <path
										d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
								  <path fill-rule="evenodd"
										d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5z" />
								</svg>`)
				).att$("href", `#/update-game/${id}`),
				button(
					span()
						.innerHTML$(`<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="#ff0000" class="bi bi-trash" viewBox="0 0 16 16">
								  <path
										d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z" />
								  <path
										d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z" />
								</svg>`)
				)
				.class$("btn btn-sm")
				.onclick$(handleDeleteGame)
			).class$("d-flex align-items-center gap-2")
		)
	)
}

const gameList = () => {
	getAll("/game", "table-games", row)

		return adminLayout(
			div(
				a(
					span().innerHTML$(`<svg
					xmlns="http://www.w3.org/2000/svg" width="16" height="16"
					fill="currentColor" class="bi bi-plus-square" viewBox="0 0 16 16">
				  <path
						d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2z" />
				  <path
						d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4" />
				</svg>`),
					span("Cadastrar novo jogo")
				)
					.class$("btn btn-light btn-sm")
					.att$("href", "#/register-game")
			).class$("w-100 d-flex justify-content-end mb-3"),
			span("Jogos cadastrados no sistemas").class$("fw-bold"),
			div(
				table(
					thead(
						tr(
							td("Id"),
							td("Nome"),
							td("Ações"),
						)
					),
					tbody()
						.id$("table-games")
				)
					.class$("table table-bordered table-dark table-hover")
			).class$("table-responsive")
		);
}

export default gameList;