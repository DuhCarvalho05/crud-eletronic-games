import { getAll, getUnique } from "../../api/services.js";
import cardgame from "../../components/cardgame.js";
import categories from "../../components/categories.js";
import layout from "../../layouts/layout.js";

const title = (props) => {
	const { name } = props;
	
	return span(name.toLowerCase()).class$("fw-bold text-capitalize text-light text-capitalize")
}

const catalog = (props) => {
	const categoryId = props[0];

	getUnique(`/category?categoryId=${categoryId}`, "category-name" ,title)

	getAll(`/category/${categoryId}`, "content", cardgame);
	
	return layout(
		div(
			categories(),
			div(
				div().id$("category-name"),
				div().id$("content").class$("d-flex flex-wrap gap-2")
			).class$("col-9")
		).class$("row justify-content-between")
	);
};

export default catalog;
