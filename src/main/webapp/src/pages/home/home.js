import { getAll } from "../../api/services.js";
import cardgame from "../../components/cardgame.js";
import categories from "../../components/categories.js";
import layout from "../../layouts/layout.js";

const home = () => {
	getAll("/game", "content", cardgame);

	return layout(
		div(
			categories(),
			div(
				span("Todos os jogos").class$("fw-bold text-capitalize text-light"),
				div().id$("content").class$("d-flex flex-wrap gap-2")
			).class$("col-9")
		).class$("row justify-content-between")
	);
};

export default home;
