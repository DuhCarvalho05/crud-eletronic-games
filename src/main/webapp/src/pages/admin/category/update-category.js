import layout from "../../../layouts/layout.js"
import { getUnique, onUpdateCategory } from "../../../api/services.js"

const inputName = (props) => {
	
	const { name } = props;
	
	return div(
		label("Nome categoria").class$("form-label"),
		input("text")
			.class$("form-control me-2 input")
			.style$("background: #000; border-color: #27272a; color: #fafafa; font-size: 14px;")
			.att$("name", "name")
			.att$("placeholder", "Nome categoria")
			.att$("value", name)
	).class$("mb-3")
}

const updateCategory = (props) => {

	const id = props[0];

	getUnique(`/category?categoryId=${id}`, "inputName", inputName)

	const handleUpdateCategory = async (e) => {
		e.preventDefault();

		const form = new FormData(document.getElementById("update-category"));

		const name = form.get("name");

		await onUpdateCategory(id, JSON.stringify({ name }));
	}

	const handlePressEnter = (e) => {
		if (e.keyCode === 13) {
			e.preventDefault();
			handleUpdateCategory(e);
		}
	}

	return layout(
		div(
			div(
				span("Atualizar categoria").class$("fw-bold fs-4 text-center"),
				form(
					div().id$("inputName"),
					div(
						a("Cancelar")
							.class$("btn btn-secondary btn-sm")
							.att$("href", "#/categories"),
						button("Atualizar categoria")
							.class$("btn btn-light btn-sm")
							.att$("type", "submit")
							.onclick$(handleUpdateCategory)
					).class$("d-flex gap-3 justify-content-end")
				)
					.id$("update-category")
					.att$("method", "POST")
					.onkeydown$(handlePressEnter)
			).class$("w-50 p-3")
		).class$("w-full d-flex justify-content-center mt-5")
	)
}

export default updateCategory;